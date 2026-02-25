package com.example.proyecte_esgrima.services;

import com.example.proyecte_esgrima.model.dto.*;
import com.example.proyecte_esgrima.model.Role;
import com.example.proyecte_esgrima.model.Usuari;

import java.util.List;


public interface UsuariService {
    
	void register(RegisterRequest request) throws Exception;
	String login(LoginRequest request) throws Exception;
    Usuari getPerfilById(String id) throws Exception;
    Usuari getPerfilByEmail(String email) throws Exception;
    List<Usuari> getAllUsuaris();
    Usuari updatePerfil(String id, Usuari request) throws Exception;
    Usuari updateRol(String id, Role rol) throws Exception;
    void deleteUsuari(String id) throws Exception;
}
