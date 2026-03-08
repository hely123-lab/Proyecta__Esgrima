package com.example.proyecte_esgrima.controller;


import com.example.proyecte_esgrima.model.Usuari;
import com.example.proyecte_esgrima.model.dto.UpdateUsuariRequest;
import com.example.proyecte_esgrima.model.enums.Role;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Gestió del perfil dels esgrimistes. Tots els endpoints requereixen JWT.
 */
@RestController
@RequestMapping("/api/usuaris")
@Tag(name = "Usuaris", description = "Gestió del perfil dels esgrimistes")
@SecurityRequirement(name = "bearerAuth")
public class UsuariController {

    private final UsuariService usuariService;

    public UsuariController(UsuariService usuariService) {
        this.usuariService = usuariService;
    }

    @GetMapping("/me")
    @Operation(summary = "Obtenir el meu perfil")
    public ResponseEntity<Usuari> getMyPerfil(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(usuariService.getPerfilByEmail(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir perfil per ID")
    public ResponseEntity<Usuari> getById(@PathVariable String id)  {
        return ResponseEntity.ok(usuariService.getPerfilById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Llistar tots els usuaris (ADMIN)")
    public ResponseEntity<List<Usuari>> getAll() {
        return ResponseEntity.ok(usuariService.getAllUsuaris());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar perfil")
    public ResponseEntity<Usuari> updatePerfil(
            @PathVariable String id,
            @Valid @RequestBody UpdateUsuariRequest request)  {
        return ResponseEntity.ok(usuariService.updatePerfil(id, request));
    }

    @PutMapping("/{id}/rol")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Canviar rol d'un usuari (ADMIN)")
    public ResponseEntity<Usuari> updateRol(
            @PathVariable String id,
            @RequestParam Role rol)  {
        return ResponseEntity.ok(usuariService.updateRol(id, rol));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Eliminar usuari (ADMIN)")
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        usuariService.deleteUsuari(id);
        return ResponseEntity.noContent().build();
    }
}
