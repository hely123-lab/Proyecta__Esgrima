package com.example.proyecto_esgrima.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecte_esgrima.model.UserLogin;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String>{

}
