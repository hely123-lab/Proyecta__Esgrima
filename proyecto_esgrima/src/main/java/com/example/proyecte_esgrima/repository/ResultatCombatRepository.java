package com.example.proyecte_esgrima.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.ResultatCombat;

/**
 * Repositorio de la colección "resultats_combat" en MongoDB.
 *
 * Hereda de MongoRepository para las operaciones básicas.
 */
@Repository
public interface ResultatCombatRepository extends MongoRepository<ResultatCombat, String> {

	Optional<ResultatCombat> findByReservaId(String reservaId);

	List<ResultatCombat> findByEsgrimista1IdOrEsgrimista2Id(String esgrimista1Id, String esgrimista2Id);

	long countByGuanyadorId(String guanyadorId);
}