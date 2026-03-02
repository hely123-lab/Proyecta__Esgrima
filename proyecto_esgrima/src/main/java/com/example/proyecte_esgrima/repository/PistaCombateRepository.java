package com.example.proyecte_esgrima.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.PistaCombate;
import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

@Repository
public interface PistaCombateRepository extends MongoRepository<PistaCombate, String> {
    Optional<PistaCombate> findByNom(String nom);
    boolean existsByNom(String nom);
    List<PistaCombate> findByDisponible(boolean disponible);
    List<PistaCombate> findByTipusArma(ArmaEsgrima tipusArma);
}