package com.example.proyecte_esgrima.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.Usuari;

/**
 * Repositorio de la colección "usuaris" en MongoDB.
 *
 * Hereda de MongoRepository, que ya proporciona las operaciones básicas.
 */
@Repository
public interface UsuariRepository extends MongoRepository<Usuari, String> {

	Optional<Usuari> findByEmail(String email);

	boolean existsByEmail(String email);

}
