package com.example.proyecte_esgrima.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecte_esgrima.exception.RecursNotFoundException;
import com.example.proyecte_esgrima.exception.ReglaNegociException;
import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.ResultatCombat;
import com.example.proyecte_esgrima.model.dto.EstadistiquesResponse;
import com.example.proyecte_esgrima.model.dto.ResultatCombatRequest;
import com.example.proyecte_esgrima.model.dto.ResultatCombatResponse;
import com.example.proyecte_esgrima.model.enums.EstadoReserva;
import com.example.proyecte_esgrima.repository.ReservaRepository;
import com.example.proyecte_esgrima.repository.ResultatCombatRepository;
import com.example.proyecte_esgrima.services.ResultatCombatService;

/**
 * Implementacion del servicio ResultatCombatService.
 *
 * Contiene toda la logica de negocio relacionada con los resultados del
 * combate: registro, consultas y calculo de estadisticas.
 */

@Service
public class ResultatCombatServiceImpl implements ResultatCombatService {

	private final ResultatCombatRepository resultatRepository;
	private final ReservaRepository reservaRepository;

	public ResultatCombatServiceImpl(ResultatCombatRepository resultatRepository, ReservaRepository reservaRepository) {
		this.resultatRepository = resultatRepository;
		this.reservaRepository = reservaRepository;
	}

	/**
	 * Registra el resultado de un combate, valida si la reserca existe y esta en
	 * CONFIRMED, Comprueba que la reserva no tenga un resultado registrado, calcula
	 * el ganador(null si empata) y guarda el resultado y actualiza el estado de la
	 * reserva a COMPLETED en la BD.
	 */
	@Override
	public ResultatCombatResponse registrarResultat(ResultatCombatRequest request) throws Exception {

		// 1. Buscamos la reserva vinculada al combate
		Reserva reserva = reservaRepository.findById(request.getReservaId())
				.orElseThrow(() -> new RecursNotFoundException("Reserva", request.getReservaId()));

		// 2. La reserva tiene que estar en CONFIRMED para poder registrar resultado.
		if (reserva.getEstat() != EstadoReserva.CONFIRMED) {
			throw new ReglaNegociException(
					"Només es pot registrar el resultat d'una reserva confirmada. Estat actual: " + reserva.getEstat());
		}

		// 3. Comprovamos que no exista un resultado para esta reserva.
		if (resultatRepository.findByReservaId(request.getReservaId()).isPresent()) {
			throw new ReglaNegociException(
					"Ja existeix un resultat registrat per a la reserva: " + request.getReservaId());
		}

		// 4. Calculamos el ganador segun los puntos
		String guanyadorId;
		if (request.getPuntsEsgrimista1() > request.getPuntsEsgrimista2()) {
			guanyadorId = reserva.getEsgrimista1Id();
		} else if (request.getPuntsEsgrimista2() > request.getPuntsEsgrimista1()) {
			guanyadorId = reserva.getEsgrimista2Id();
		} else {
			// Empate: no hay ganador
			guanyadorId = null;
		}

		// 5. Creamos y guardamos el resultado
		ResultatCombat resultat = new ResultatCombat();
		resultat.setReservaId(request.getReservaId());
		resultat.setEsgrimista1Id(reserva.getEsgrimista1Id());
		resultat.setEsgrimista2Id(reserva.getEsgrimista2Id());
		resultat.setPuntsEsgrimista1(request.getPuntsEsgrimista1());
		resultat.setPuntsEsgrimista2(request.getPuntsEsgrimista2());
		resultat.setGuanyadorId(guanyadorId);
		resultat.setDuracioMinuts(request.getDuracioMinuts());

		resultat = resultatRepository.save(resultat);

		// 6. Y marcamos la reserva como COMPLETED
		reserva.setEstat(EstadoReserva.COMPLETED);
		reservaRepository.save(reserva);

		return toResponse(resultat);
	}

	@Override
	public ResultatCombatResponse getById(String id) throws Exception {
		ResultatCombat resultat = resultatRepository.findById(id)
				.orElseThrow(() -> new RecursNotFoundException("Resultat de combat", id));
		return toResponse(resultat);
	}

	@Override
	public List<ResultatCombatResponse> getHistorialByUsuari(String usuariId) {
		List<ResultatCombat> resultats = resultatRepository.findByEsgrimista1IdOrEsgrimista2Id(usuariId, usuariId);
		List<ResultatCombatResponse> resultat = new ArrayList<>();
		for (ResultatCombat r : resultats) {
			resultat.add(toResponse(r));
		}
		return resultat;
	}

	@Override
	public EstadistiquesResponse getEstadistiques(String usuariId) {
		// Total de combate jugados tanto como esgrimista 1 o 2
		long totalCombats = resultatRepository.findByEsgrimista1IdOrEsgrimista2Id(usuariId, usuariId).size();
		long victories = resultatRepository.countByGuanyadorId(usuariId);
		// El constructor de EstadistiquesResponse calcula derrotas i porcentages
		return new EstadistiquesResponse(usuariId, totalCombats, victories);
	}

	/**
	 * 
	 * Metodo privado auxiliar para convertir el modelo ResultatCombat en el DTO
	 * ResultatCombatResponse
	 *
	 * @param Resultado el documento del modelo
	 * @return el DTO de resposta
	 */
	private ResultatCombatResponse toResponse(ResultatCombat resultat) {
		ResultatCombatResponse response = new ResultatCombatResponse();
		response.setId(resultat.getId());
		response.setReservaId(resultat.getReservaId());
		response.setEsgrimista1Id(resultat.getEsgrimista1Id());
		response.setEsgrimista2Id(resultat.getEsgrimista2Id());
		response.setPuntsEsgrimista1(resultat.getPuntsEsgrimista1());
		response.setPuntsEsgrimista2(resultat.getPuntsEsgrimista2());
		response.setGuanyadorId(resultat.getGuanyadorId());
		response.setDuracioMinuts(resultat.getDuracioMinuts());
		response.setDataCombat(resultat.getDataCombat());
		return response;
	}
}