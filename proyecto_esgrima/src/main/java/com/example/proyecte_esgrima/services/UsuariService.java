package com.example.proyecte_esgrima.services;

import java.util.List;

import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.model.dto.AuthResponse;
import com.example.proyecte_esgrima.model.dto.LoginRequest;
import com.example.proyecte_esgrima.model.dto.RegisterRequest;
import com.example.proyecte_esgrima.model.dto.UpdateUsuariRequest;
import com.example.proyecte_esgrima.model.enums.Role;

public interface UsuariService {

	AuthResponse register(RegisterRequest request);

	AuthResponse login(LoginRequest request) ;

	Usuari getPerfilById(String id) ;

	Usuari getPerfilByEmail(String email);

	List<Usuari> getAllUsuaris();

	Usuari updateRol(String id, Role rol);

	void deleteUsuari(String id) ;

	Usuari updatePerfil(String id, UpdateUsuariRequest request);
}
