package com.example.proyecte_esgrima.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecte_esgrima.model.dto.AuthResponse;
import com.example.proyecte_esgrima.model.dto.LoginRequest;
import com.example.proyecte_esgrima.model.dto.RegisterRequest;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller de autenticación. Gestiona el registro y el login de usuarios.
 *
 * no requieren token JWT. Están configuradas como permitAll() en
 * SecurityConfig.
 *
 * 
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticació", description = "Registre i login d'esgrimistes")
public class AuthController {

	private final UsuariService usuariService;

	public AuthController(UsuariService usuariService) {
		this.usuariService = usuariService;
	}

	/**
	 * POST /auth/register
	 *
	 * Registra un nuevo usuario en el sistema. Devuelve HTTP 201 Created con los
	 * datos del usuario creado.
	 *
	 * @param request datos del registro (nom, cognoms, email, password, etc.)
	 * @return AuthResponse con el email del usuario creado
	 */
	@PostMapping("/register")
	@Operation(summary = "Registre d'un nou esgrimista", description = "Crea un nou compte. Retorna un JWT vàlid.")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariService.register(request));
	}

	/**
	 * POST /auth/login
	 *
	 * Autentica un usuario con email y contraseña. Devuelve HTTP 200 OK.
	 *
	 * @param request credenciales de acceso (email y password)
	 * @return AuthResponse con el token JWT, email, rol y nombre del usuario
	 */
	@PostMapping("/login")
	@Operation(summary = "Login d'un esgrimista", description = "Autentica l'usuari amb email i contrasenya. Retorna un JWT vàlid.")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
		return ResponseEntity.ok(usuariService.login(request));
	}
}
