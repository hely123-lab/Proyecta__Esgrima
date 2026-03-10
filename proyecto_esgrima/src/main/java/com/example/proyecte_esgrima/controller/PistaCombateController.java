package com.example.proyecte_esgrima.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecte_esgrima.model.dto.PistaCombateRequest;
import com.example.proyecte_esgrima.model.dto.PistaCombateResponse;
import com.example.proyecte_esgrima.services.PistaCombateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller para la gestión de las pistas de combate del centro de esgrima.
 * Todos los endpoints requieren un token JWT válido.
 *
 */
@RestController
@RequestMapping("/api/pistes")
@Tag(name = "Pistes de Combat", description = "Gestió de les pistes de combat")
@SecurityRequirement(name = "bearerAuth")
public class PistaCombateController {

	private final PistaCombateService pistaService;

	public PistaCombateController(PistaCombateService pistaService) {
		this.pistaService = pistaService;
	}

	/**
	 * POST /api/pistes
	 *
	 * Crea una nueva pista de combate. Solo accesible por ADMIN. Devuelve HTTP 201
	 * Created con los datos de la pista creada.
	 *
	 * @param request datos de la nueva pista (nom, descripcio, tipusArma)
	 * @return la pista creada con HTTP 201
	 */
	@PostMapping
	@Operation(summary = "Crear pista (ADMIN)")
	public ResponseEntity<PistaCombateResponse> crear(@Valid @RequestBody PistaCombateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pistaService.crear(request));
	}

	/**
	 * GET /api/pistes
	 *
	 * Devuelve la lista de todas las pistas del centro, tanto las disponibles como
	 * las no disponibles.
	 *
	 * @return lista de todas las pistas con HTTP 200
	 */

	@GetMapping
	@Operation(summary = "Llistar totes les pistes")
	public ResponseEntity<List<PistaCombateResponse>> getAll() {
		return ResponseEntity.ok(pistaService.getAll());
	}

	/**
	 * GET /api/pistes/disponibles
	 *
	 * Devuelve solo las pistas marcadas como disponibles (disponible = true).
	 *
	 * @return lista de pistas disponibles con HTTP 200
	 */
	@GetMapping("/disponibles")
	@Operation(summary = "Llistar pistes disponibles")
	public ResponseEntity<List<PistaCombateResponse>> getDisponibles() {
		return ResponseEntity.ok(pistaService.getDisponibles());
	}

	/**
	 * GET /api/pistes/{id}
	 *
	 * Devuelve los datos de una pista concreta por su ID.
	 *
	 * @param id ID de la pista
	 * @return la pista con HTTP 200, o 404 si no existe
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtenir pista per ID")
	public ResponseEntity<PistaCombateResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(pistaService.getById(id));
	}

	/**
	 * PUT /api/pistes/{id}
	 *
	 * Actualiza los datos de una pista existente. Solo accesible por ADMIN.
	 *
	 * @param id      ID de la pista a actualizar
	 * @param request nuevos datos de la pista
	 * @return la pista actualizada con HTTP 200
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Actualitzar pista (ADMIN)")
	public ResponseEntity<PistaCombateResponse> update(@PathVariable String id,
			@Valid @RequestBody PistaCombateRequest request) {
		return ResponseEntity.ok(pistaService.update(id, request));
	}

	/**
	 * DELETE /api/pistes/{id}
	 *
	 * Elimina una pista del sistema. Solo accesible por ADMIN. Devuelve HTTP 204 si
	 * se elimina correctamente.
	 *
	 * @param id ID de la pista a eliminar
	 * @return respuesta vacía con HTTP 204
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar pista (ADMIN)")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		pistaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
