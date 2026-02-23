package com.example.proyecte_esgrima.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.Pista;

public interface PistaRepository extends MongoRepository<Pista, String> {
}
