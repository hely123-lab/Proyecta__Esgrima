package com.example.proyecte_esgrima.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.Usuari;

public interface UsuariRepository extends MongoRepository<Usuari, String> {

	Optional<Usuari> findByEmail(String email);

	boolean existsByEmail(String email);
}
