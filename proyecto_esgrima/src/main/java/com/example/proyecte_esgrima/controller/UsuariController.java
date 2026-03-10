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
 * Controller para la gestión del perfil de los esgrimistas.
 * Todos los endpoints requieren un token JWT válido en la cabecera Authorization.
 *
 * 
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

    /**
     * GET /api/usuaris/me
     *
     * Devuelve el perfil del usuario que está haciendo la petición.
     *
     * @param userDetails datos del usuario autenticado.
     * @return el perfil del usuario con HTTP 200
     */
    @GetMapping("/me")
    @Operation(summary = "Obtenir el meu perfil")
    public ResponseEntity<Usuari> getMyPerfil(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(usuariService.getPerfilByEmail(userDetails.getUsername()));
    }

    /**
     * GET /api/usuaris/{id}
     *
     * Devuelve el perfil de un usuario concreto por su ID.
     * Accesible por cualquier usuario autenticado.
     *
     * @param id ID del usuario a buscar
     * @return el perfil del usuario con HTTP 200
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir perfil per ID")
    public ResponseEntity<Usuari> getById(@PathVariable String id)  {
        return ResponseEntity.ok(usuariService.getPerfilById(id));
    }

    /**
     * GET /api/usuaris
     *
     * Devuelve la lista completa de todos los usuarios registrados.
     *
     * @return lista de todos los usuarios con HTTP 200
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Llistar tots els usuaris (ADMIN)")
    public ResponseEntity<List<Usuari>> getAll() {
        return ResponseEntity.ok(usuariService.getAllUsuaris());
    }

    /**
     * PUT /api/usuaris/{id}
     *
     * Actualiza los datos del perfil de un usuario.
  
     *
     * @param id ID del usuario a modificar
     * @param request nuevos datos del perfil (nom, cognoms, dataNaixement, sexe, nivell)
     * @return el usuario actualizado con HTTP 200
     */
    @PutMapping("/{id}")
    @Operation(summary = "Modificar perfil")
    public ResponseEntity<Usuari> updatePerfil(
            @PathVariable String id,
            @Valid @RequestBody UpdateUsuariRequest request)  {
        return ResponseEntity.ok(usuariService.updatePerfil(id, request));
    }

    
    /**
     * PUT /api/usuaris/{id}/?rol=ROLE_ADMIN
     *
     * Cambia el rol de un usuario. Solo accesible por ADMIN.
     * El rol se pasa como parámetro de la URL.
     *
     * @param id  ID del usuario
     * @param rol nuevo rol a asignar (ROLE_USER o ROLE_ADMIN)
     * @return el usuario con el rol actualizado y HTTP 200
     */
    @PutMapping("/{id}/rol")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Canviar rol d'un usuari (ADMIN)")
    public ResponseEntity<Usuari> updateRol(
            @PathVariable String id,
            @RequestParam Role rol)  {
        return ResponseEntity.ok(usuariService.updateRol(id, rol));
    }

    /**
     * DELETE /api/usuaris/{id}
     *
     * Elimina un usuario del sistema. Solo accesible por ADMIN.
     * Devuelve HTTP 204  si se elimina correctamente.
     *
     * @param id ID del usuario a eliminar
     * @return respuesta vacía con HTTP 204
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Eliminar usuari (ADMIN)")
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        usuariService.deleteUsuari(id);
        return ResponseEntity.noContent().build();
    }
}
