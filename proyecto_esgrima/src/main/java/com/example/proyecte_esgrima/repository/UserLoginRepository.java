package com.example.proyecte_esgrima.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.Usuari;

@Repository
public interface UserLoginRepository extends MongoRepository<Usuari, String>{
	
	Optional<Usuari> findByUsername(String username);


}
