package com.example.proyecte_esgrima.services;

import java.util.List;

import com.example.proyecte_esgrima.model.dto.EstadistiquesResponse;
import com.example.proyecte_esgrima.model.dto.ResultatCombatRequest;
import com.example.proyecte_esgrima.model.dto.ResultatCombatResponse;

/**
 * Interficie del servicio de ResultatCombat.
 * 
 * Definimos las operaciones disponibles para gestionar los resultados de los
 * combates y las estadisticas.
 */
public interface ResultatCombatService {

	/**
	 * Registra el resultado de un combate. la reserva vinculada tiene que estar
	 * CONFIRMED, despues de estar registrada, la reserva pasa al estado COMPLETED.
	 * 
	 * @param request dades del resultat (reservaId, punts, duració)
	 * @return el resultado guardado como DTO
	 * @throws Exception si la reserva no existe, no està confirmada o ya tiene un
	 *                   resultdo registrado.
	 */
	ResultatCombatResponse registrarResultat(ResultatCombatRequest request) throws Exception;

	/**
	 * Obtiene el resultado de un combate por la ID.
	 * 
	 *
	 * @param id ID del resultado
	 * @return el resultat como una DTO
	 * @throws Exception si no se encuentra el resultado
	 */
	ResultatCombatResponse getById(String id) throws Exception;

	/**
	 * Devuelve el historial del combate de un usuario.
	 * 
	 * @param usuariId ID del usuario
	 * @return Lista de resultados del usuario
	 */
	List<ResultatCombatResponse> getHistorialByUsuari(String usuariId);

	/**
	 * 
	 * Calcula las estadisticas basicas de un usuario: total de combates, victorias,
	 * derrotas y porcentage de victorias.
	 *
	 * 
	 * @param usuariId ID del usuario
	 * @return estadisticas del usuario
	 */
	EstadistiquesResponse getEstadistiques(String usuariId);
}