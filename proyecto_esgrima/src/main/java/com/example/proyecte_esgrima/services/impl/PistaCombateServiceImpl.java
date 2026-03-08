package com.example.proyecte_esgrima.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.proyecte_esgrima.exception.RecursNotFoundException;
import com.example.proyecte_esgrima.exception.ReglaNegociException;
import com.example.proyecte_esgrima.model.PistaCombate;
import com.example.proyecte_esgrima.model.dto.PistaCombateRequest;
import com.example.proyecte_esgrima.model.dto.PistaCombateResponse;
import com.example.proyecte_esgrima.repository.PistaCombateRepository;
import com.example.proyecte_esgrima.services.PistaCombateService;

@Service
public class PistaCombateServiceImpl implements PistaCombateService {

	private final PistaCombateRepository pistaRepository;

	public PistaCombateServiceImpl(PistaCombateRepository pistaRepository) {
		this.pistaRepository = pistaRepository;
	}

	@Override
	public PistaCombateResponse crear(PistaCombateRequest request) {
		// Comprovem que no existeixi ja una pista amb el mateix nom
		if (pistaRepository.existsByNom(request.getNom())) {
			throw new ReglaNegociException("Ja existeix una pista amb el nom: " + request.getNom());
		}

		PistaCombate pista = new PistaCombate();
		pista.setNom(request.getNom());
		pista.setDescripcio(request.getDescripcio());
		pista.setTipusArma(request.getTipusArma());
		pista.setDisponible(true);

		pista = pistaRepository.save(pista);
		return toResponse(pista);
	}

	@Override
	public PistaCombateResponse getById(String id) {
		PistaCombate pista = pistaRepository.findById(id)
				.orElseThrow(() -> new RecursNotFoundException("Pista de combat", id));
		return toResponse(pista);
	}

	@Override
	public List<PistaCombateResponse> getAll() {
		return pistaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public List<PistaCombateResponse> getDisponibles() {
		return pistaRepository.findByDisponible(true).stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public PistaCombateResponse update(String id, PistaCombateRequest request) {
		PistaCombate pista = pistaRepository.findById(id)
				.orElseThrow(() -> new RecursNotFoundException("Pista de combat", id));

		// Si canviem el nom, comprovem que el nou nom no el tingui una altra pista
		if (!pista.getNom().equals(request.getNom()) && pistaRepository.existsByNom(request.getNom())) {
			throw new ReglaNegociException("Ja existeix una pista amb el nom: " + request.getNom());
		}

		pista.setNom(request.getNom());
		pista.setDescripcio(request.getDescripcio());
		pista.setTipusArma(request.getTipusArma());

		pista = pistaRepository.save(pista);
		return toResponse(pista);
	}

	@Override
	public void delete(String id) {
		if (!pistaRepository.existsById(id)) {
			throw new RecursNotFoundException("Pista de combat", id);
		}
		pistaRepository.deleteById(id);
	}

	/*
	 * Mètode privat auxiliar que converteix un PistaCombate (model) en un
	 * PistaCombateResponse (DTO). Així no repetim aquest codi en cada mètode del
	 * service.
	 */
	private PistaCombateResponse toResponse(PistaCombate pista) {
		PistaCombateResponse response = new PistaCombateResponse();
		response.setId(pista.getId());
		response.setNom(pista.getNom());
		response.setDescripcio(pista.getDescripcio());
		response.setTipusArma(pista.getTipusArma());
		response.setDisponible(pista.isDisponible());
		return response;
	}
}