package com.example.proyecto_esgrima.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.UserLogin;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String>{
	
	Optional<UserLogin> findByUsername(String username);


}
