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
 * Controller para la gestión de resultados de combates y estadísticas. Todos
 * los endpoints requieren un token JWT válido.
 *
 * 
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
	 * Registra el resultado de un combate. Solo accesible por ADMIN. La reserva
	 * vinculada debe estar en estado CONFIRMED. Una vez registrado el resultado, la
	 * reserva pasa automáticamente a COMPLETED. Devuelve HTTP 201.
	 *
	 * @param request datos del resultado (reservaId, puntos de cada esgrimista,
	 *                duración)
	 * @return el resultado registrado con HTTP 201
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
	 * @return el resultado con HTTP 200, o 404 si no existe
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtenir un resultat per ID")
	public ResponseEntity<ResultatCombatResponse> getById(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(resultatService.getById(id));
	}

	/**
	 * GET /api/resultats/meus
	 *
	 * Devuelve el historial de combates del usuario autenticado.
	 *
	 * @param userDetails datos del usuario autenticado.
	 * @return lista de resultados de combates con HTTP 200
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
	 * Devuelve las estadísticas de combate del usuario autenticado: total de
	 * combates, victorias, derrotas y porcentaje de victorias.
	 *
	 * @param userDetails datos del usuario autenticado
	 * @return EstadistiquesResponse con las estadísticas del usuario, HTTP 200
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
	 * Devuelve las estadísticas de un usuario concreto por su ID. Solo accesible
	 * por ADMIN.
	 * 
	 * @param usuariId ID del usuario.
	 * @return EstadistiquesResponse con las estadísticas del usuario, HTTP 200
	 */
	@GetMapping("/estadistiques/{usuariId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Estadístiques d'un usuari per ID (ADMIN)")
	public ResponseEntity<EstadistiquesResponse> getEstadistiquesById(@PathVariable String usuariId) {
		return ResponseEntity.ok(resultatService.getEstadistiques(usuariId));
	}
}