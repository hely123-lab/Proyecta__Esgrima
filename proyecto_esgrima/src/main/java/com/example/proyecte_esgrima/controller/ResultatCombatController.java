package com.example.proyecte_esgrima.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecte_esgrima.model.dto.EstadistiquesResponse;
import com.example.proyecte_esgrima.model.dto.ResultatCombatRequest;
import com.example.proyecte_esgrima.model.dto.ResultatCombatResponse;
import com.example.proyecte_esgrima.services.ResultatCombatService;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller para gestionar los resultados de combates y estadisticas con todos
 * los enspointd requeridos. Algunos endpoints estan restringidos con el rol
 * ADMIN.
 *
 * Base URL: /api/resultats
 */
@RestController
@RequestMapping("/api/resultats")
@Tag(name = "Resultats", description = "Registre de resultats de combats i estadístiques")
@SecurityRequirement(name = "bearerAuth")
public class ResultatCombatController {

	private final ResultatCombatService resultatService;
	private final UsuariService usuariService;

	public ResultatCombatController(ResultatCombatService resultatService, UsuariService usuariService) {
		this.resultatService = resultatService;
		this.usuariService = usuariService;
	}

	/**
	 * POST /api/resultats
	 *
	 * Registra el resultado de un combate. solo accesible por ADMIN. la reserva
	 * tiene que estar en CONFIRMED y cuando ya se registra el resultao se pasa a
	 * COMPLETED.
	 *
	 * @param request dades del resultat (reservaId, punts, duració)
	 * @return el resultado creado con el codigo HTTP 201
	 */
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Registrar resultat d'un combat (ADMIN)", description = "Registra el resultat d'una reserva CONFIRMED i la marca com COMPLETED.")
	public ResponseEntity<ResultatCombatResponse> registrar(@Valid @RequestBody ResultatCombatRequest request)
			throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(resultatService.registrarResultat(request));
	}

	/**
	 * GET /api/resultats/{id}
	 *
	 * Devuelve el resultado de un combate por su ID.
	 *
	 * @param id ID del resultado a buscar
	 * @return el resultado encontrado con el codigo HTTP 200
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtenir un resultat per ID")
	public ResponseEntity<ResultatCombatResponse> getById(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(resultatService.getById(id));
	}

	/**
	 * GET /api/resultats/meus
	 *
	 * Devuelve el historial de combates de un usuario.
	 *
	 * @param userDetails datos del usuario autenticado, injectados por Spring
	 *                    Security
	 * @return Lista de resultado con el codigo HTTP 200
	 */
	@GetMapping("/meus")
	@Operation(summary = "Historial dels meus combats", description = "Retorna tots els resultats de combats de l'usuari autenticat.")
	public ResponseEntity<List<ResultatCombatResponse>> getMeusResultats(
			@AuthenticationPrincipal UserDetails userDetails) throws Exception {
		String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
		return ResponseEntity.ok(resultatService.getHistorialByUsuari(usuariId));
	}

	/**
	 * GET /api/resultats/estadistiques/me
	 *
	 * Devuelve las estadisticas del usuario. total combates, victorias, derrotas y
	 * porcentage de victorias.
	 *
	 * @param userDetails datos del usuario autenticado, injectados por Spring
	 *                    Security
	 * @return estadisticas con el codigo HTTP 200
	 */
	@GetMapping("/estadistiques/me")
	@Operation(summary = "Les meves estadístiques", description = "Retorna el total de combats, victòries, derrotes i % de victòries.")
	public ResponseEntity<EstadistiquesResponse> getMevesEstadistiques(@AuthenticationPrincipal UserDetails userDetails)
			throws Exception {
		String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
		return ResponseEntity.ok(resultatService.getEstadistiques(usuariId));
	}

	/**
	 * GET /api/resultats/estadistiques/{usuariId}
	 *
	 * Devuelve las estadisticas de un usuario concreto por su ID. solo accesible
	 * por ADMIN, para consultar cualquier usuario.
	 * 
	 * @param usuariId ID del usuario a cunsultar
	 * @return estadisticas con el codigo HTTP 200
	 */
	@GetMapping("/estadistiques/{usuariId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Estadístiques d'un usuari per ID (ADMIN)")
	public ResponseEntity<EstadistiquesResponse> getEstadistiquesById(@PathVariable String usuariId) {
		return ResponseEntity.ok(resultatService.getEstadistiques(usuariId));
	}
}