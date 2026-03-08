package com.example.proyecte_esgrima.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.ResultatCombat;

/**
 * Repositorio de ResultatCombat. Hereda de MongoRepository, para utilizar el
 * save,findByid, findAll, etc..
 */

@Repository
public interface ResultatCombatRepository extends MongoRepository<ResultatCombat, String> {

	/**
	 * Busca el resultado asociado a la reserva concreta.
	 * 
	 * Devuelve Optional porque puede que la reserva aun no tenga resultado.
	 *
	 * @param reservaId ID de la reserva
	 * @return el resultat si existe, o vacio si no
	 */
	Optional<ResultatCombat> findByReservaId(String reservaId);

	/**
	 * 
	 * Busca todos los combates en el que particia el usuario, tanto si ha sido
	 * esgrimista 1 o 2.
	 *
	 * @param esgrimista1Id ID del usuario como esgrimista 1
	 * @param esgrimista2Id ID del usuario como esgrimista 2
	 * @return Lista de resultados de combates
	 */
	List<ResultatCombat> findByEsgrimista1IdOrEsgrimista2Id(String esgrimista1Id, String esgrimista2Id);

	/**
	 * 
	 * Cuenta la cantidad de veces que ha ganado el usuario lo utilizamos para
	 * calcular estadisticas.
	 * 
	 * @param guanyadorId ID del usuario ganador
	 * @return cantidad de victorias
	 */
	long countByGuanyadorId(String guanyadorId);
}