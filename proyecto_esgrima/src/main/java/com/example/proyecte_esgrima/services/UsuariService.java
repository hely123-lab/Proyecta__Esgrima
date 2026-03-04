package com.example.proyecte_esgrima.services;

import java.util.List;

import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.model.dto.AuthResponse;
import com.example.proyecte_esgrima.model.dto.LoginRequest;
import com.example.proyecte_esgrima.model.dto.RegisterRequest;
import com.example.proyecte_esgrima.model.dto.UpdateUsuariRequest;
import com.example.proyecte_esgrima.model.enums.Role;

public interface UsuariService {

	AuthResponse register(RegisterRequest request) throws Exception;

	AuthResponse login(LoginRequest request) throws Exception;

	Usuari getPerfilById(String id) throws Exception;

	Usuari getPerfilByEmail(String email) throws Exception;

	List<Usuari> getAllUsuaris();

	Usuari updateRol(String id, Role rol) throws Exception;

	void deleteUsuari(String id) throws Exception;

	Usuari updatePerfil(String id, UpdateUsuariRequest request) throws Exception;
}
