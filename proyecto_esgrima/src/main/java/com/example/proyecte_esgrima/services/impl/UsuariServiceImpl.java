package com.example.proyecte_esgrima.services.impl;


import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.model.dto.*;
import com.example.proyecte_esgrima.model.Role;
import com.example.proyecte_esgrima.repository.UsuariRepository;
import com.example.proyecte_esgrima.security.JwtUtil;
import com.example.proyecte_esgrima.services.UsuariService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariServiceImpl implements UsuariService {

    private final UsuariRepository usuariRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UsuariServiceImpl(UsuariRepository usuariRepository,
                              PasswordEncoder passwordEncoder,
                              JwtUtil jwtUtil,
                              AuthenticationManager authenticationManager) {
        this.usuariRepository    = usuariRepository;
        this.passwordEncoder     = passwordEncoder;
        this.jwtUtil             = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequest request) throws Exception {
        if (usuariRepository.existsByEmail(request.getEmail())) {
            throw new Exception(
                    "Ja existeix un usuari amb l'email: " + request.getEmail());
        }
        Usuari usuari = new Usuari(
                request.getNom(),
                request.getCognoms(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getDataNaixement(),
                request.getSexe(),
                request.getNivell(),
                Role.ROLE_USER
        );
        usuariRepository.save(usuari);
   
    }

    @Override
    public String login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if(!usuariRepository.existsByEmail(request.getEmail())) {
        	throw new Exception("El usuari amb email " + request.getEmail() + " no existeix");
        }
        Optional<Usuari> usuari = usuariRepository.findByEmail(request.getEmail());
        String token = jwtUtil.generateToken(usuari.get());
        return token;
    }

    @Override
    public Usuari getPerfilById(String id) throws Exception {
        return (usuariRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuari amb id " + id + "no trobat")));
    }

    @Override
    public Usuari getPerfilByEmail(String email) throws Exception {
        return (usuariRepository.findByEmail(email)
                .orElseThrow(() -> new Exception(
                        "Usuari no trobat amb email: " + email)));
    }

    @Override
    public List<Usuari> getAllUsuaris() {
        return usuariRepository.findAll();
    }

    @Override
    public Usuari updatePerfil(String id, Usuari request) throws Exception {
        Usuari usuari = usuariRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuari amb id " +  id + " no trobat"));
        usuari.setNom(request.getNom());
        usuari.setCognoms(request.getCognoms());
        usuari.setDataNaixement(request.getDataNaixement());
        usuari.setSexe(request.getSexe());
        usuari.setNivell(request.getNivell());
        return (usuariRepository.save(usuari));
    }

    @Override
    public Usuari updateRol(String id, Role rol) throws Exception {
        Usuari usuari = usuariRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuari amb id " + id + " no trobat"));
        usuari.setRol(rol);
        return (usuariRepository.save(usuari));
    }

    @Override
    public void deleteUsuari(String id) throws Exception {
        if (!usuariRepository.existsById(id)) {
            throw new Exception("Usuari amb id " + id + " no trobat");
        }
        usuariRepository.deleteById(id);
    }



	
}
