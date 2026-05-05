package com.example.proyecte_esgrima.services.impl;

import com.example.proyecte_esgrima.exception.RecursNotFoundException;
import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.model.dto.*;
import com.example.proyecte_esgrima.model.enums.Role;
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

	public UsuariServiceImpl(UsuariRepository usuariRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
			AuthenticationManager authenticationManager) {
		this.usuariRepository = usuariRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public AuthResponse register(RegisterRequest request) {
		if (usuariRepository.existsByEmail(request.getEmail())) {
			throw new RecursNotFoundException("Usuari", request.getEmail());
		}
		Usuari usuari = new Usuari(request.getNom(), request.getCognoms(), request.getEmail(),
				passwordEncoder.encode(request.getPassword()), request.getDataNaixement(), request.getSexe(),
				request.getNivell(), Role.ROLE_USER);
		usuariRepository.save(usuari);
		String token = jwtUtil.generateToken(usuari);
		AuthResponse responseCreated = new AuthResponse();
		responseCreated.setEmail(usuari.getEmail());
		responseCreated.setToken(token);
		return responseCreated;

	}

	@Override
	public AuthResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		if (!usuariRepository.existsByEmail(request.getEmail())) {
			throw new RecursNotFoundException("Usuari", request.getEmail());
		}
		Optional<Usuari> usuari = usuariRepository.findByEmail(request.getEmail());
		String token = jwtUtil.generateToken(usuari.get());
		return new AuthResponse(token, usuari.get().getEmail(), usuari.get().getRol().name(), usuari.get().getNom());
	}

	@Override
	public Usuari getPerfilById(String id) {
		return (usuariRepository.findById(id).orElseThrow(() -> new RecursNotFoundException("usuari", id)));
	}

	@Override
	public Usuari getPerfilByEmail(String email) {
		return (usuariRepository.findByEmail(email).orElseThrow(() -> new RecursNotFoundException("Usuari", email)));
	}

	@Override
	public List<Usuari> getAllUsuaris() {
		return usuariRepository.findAll();
	}

	@Override
	public Usuari updatePerfil(String id, UpdateUsuariRequest request) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow(() -> new RecursNotFoundException("Usuari", id));
		usuari.setNom(request.getNom());
		usuari.setCognoms(request.getCognoms());
		usuari.setDataNaixement(request.getDataNaixement());
		usuari.setSexe(request.getSexe());
		usuari.setNivell(request.getNivell());
		return (usuariRepository.save(usuari));
	}

	@Override
	public Usuari updateRol(String id, Role rol) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow(() -> new RecursNotFoundException("Usuari", id));
		usuari.setRol(rol);
		return (usuariRepository.save(usuari));
	}

	@Override
	public void deleteUsuari(String id) {
		if (!usuariRepository.existsById(id)) {
			throw new RecursNotFoundException("Usuari", id);
		}
		usuariRepository.deleteById(id);
	}

}
