package com.example.proyecte_esgrima.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.PistaCombate;

public interface PistaRepository extends MongoRepository<PistaCombate, String> {
}
