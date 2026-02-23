package com.example.proyecte_esgrima.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}
