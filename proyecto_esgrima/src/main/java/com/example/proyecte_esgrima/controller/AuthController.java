package com.example.proyecte_esgrima.controller;

import com.example.proyecte_esgrima.model.dto.AuthResponse;
import com.example.proyecte_esgrima.model.dto.LoginRequest;
import com.example.proyecte_esgrima.model.dto.RegisterRequest;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints públics d'autenticació: registre i login.
 * No requereixen token JWT.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticació", description = "Registre i login d'esgrimistes")
public class AuthController {

    private final UsuariService usuariService;

    public AuthController(UsuariService usuariService) {
        this.usuariService = usuariService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registre d'un nou esgrimista",
               description = "Crea un nou compte. Retorna un JWT vàlid.")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login d'un esgrimista",
               description = "Autentica l'usuari amb email i contrasenya. Retorna un JWT vàlid.")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(usuariService.login(request));
    }
}
