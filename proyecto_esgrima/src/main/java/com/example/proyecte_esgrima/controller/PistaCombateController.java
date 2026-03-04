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
 * Gestió de les pistes de combat del centre d'esgrima.
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

	@PostMapping
	@Operation(summary = "Crear pista (ADMIN)")
	public ResponseEntity<PistaCombateResponse> crear(@Valid @RequestBody PistaCombateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pistaService.crear(request));
	}

	@GetMapping
	@Operation(summary = "Llistar totes les pistes")
	public ResponseEntity<List<PistaCombateResponse>> getAll() {
		return ResponseEntity.ok(pistaService.getAll());
	}

	@GetMapping("/disponibles")
	@Operation(summary = "Llistar pistes disponibles")
	public ResponseEntity<List<PistaCombateResponse>> getDisponibles() {
		return ResponseEntity.ok(pistaService.getDisponibles());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtenir pista per ID")
	public ResponseEntity<PistaCombateResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(pistaService.getById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualitzar pista (ADMIN)")
	public ResponseEntity<PistaCombateResponse> update(@PathVariable String id,
			@Valid @RequestBody PistaCombateRequest request) {
		return ResponseEntity.ok(pistaService.update(id, request));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar pista (ADMIN)")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		pistaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
