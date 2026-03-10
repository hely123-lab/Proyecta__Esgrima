package com.example.proyecte_esgrima.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.dto.ReservaRequest;
import com.example.proyecte_esgrima.services.ReservaService;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller para la gestión de reservas de pistas de combate. Todos los
 * endpoints requieren un token JWT válido.
 */
@RestController
@RequestMapping("/api/reserves")
@Tag(name = "Reserves", description = "Gestió de reserves de pistes de combat d'esgrima")
@SecurityRequirement(name = "bearerAuth")
public class ReservaController {

	private final ReservaService reservaService;
	private final UsuariService usuariService;

	public ReservaController(ReservaService reservaService, UsuariService usuariService) {
		this.reservaService = reservaService;
		this.usuariService = usuariService;
	}

	/**
	 * POST /api/reserves
	 *
	 * Crea una nueva reserva de combate para el usuario autenticado. El usuario
	 * puede reservar con un rival concreto (indicando esgrimista2Id) o activar la
	 * búsqueda automática de rival (buscarRivalAutomatic = true).
	 *
	 * @param userDetails datos del usuario autenticado.
	 * @param request     datos de la reserva (pistaId, fechas, arma, rival)
	 * @return la reserva creada con HTTP 201
	 */
	@PostMapping
	@Operation(summary = "Crear una reserva de combat", description = "Crea una reserva per a dos esgrimistes concrets o activa la cerca automàtica de rival.")
	public ResponseEntity<Reserva> crear(@AuthenticationPrincipal UserDetails userDetails,
			@Valid @RequestBody ReservaRequest request) {
		// Obtenemos el ID del usuario a partir del email del token JWT
		String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
		return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(usuariId, request));
	}

	/**
	 * GET /api/reserves/{id}
	 *
	 * Devuelve los datos de una reserva concreta por su ID.
	 *
	 * @param id ID de la reserva
	 * @return la reserva con HTTP 200, o 404 si no existe
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtenir una reserva per ID")
	public ResponseEntity<Reserva> getById(@PathVariable String id) {
		return ResponseEntity.ok(reservaService.getById(id));
	}

	/**
	 * GET /api/reserves/meves
	 *
	 * Devuelve el historial de reservas del usuario autenticado.
	 *
	 * @param userDetails datos del usuario autenticado
	 * @return lista de reservas del usuario con HTTP 200
	 */
	@GetMapping("/meves")
	@Operation(summary = "Historial de les meves reserves", description = "Retorna totes les reserves de l'usuari autenticat.")
	public ResponseEntity<List<Reserva>> getMevesReserves(@AuthenticationPrincipal UserDetails userDetails) {
		String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
		return ResponseEntity.ok(reservaService.getHistorialByUsuari(usuariId));
	}

	/**
	 * GET /api/reserves
	 *
	 * Devuelve todas las reservas del sistema. Solo accesible por ADMIN.
	 *
	 * @return lista de todas las reservas con HTTP 200
	 */
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Llistar totes les reserves (ADMIN)")
	public ResponseEntity<List<Reserva>> getAll() {
		return ResponseEntity.ok(reservaService.getAll());
	}

	/**
	 * GET /api/reserves/disponibilitat?pistaId=&inici=&fi=
	 *
	 * Consulta las reservas existentes de una pista en un rango de fechas. Sirve
	 * para que el usuario pueda saber si una pista está ocupada antes de reservar.
	 *
	 * Las fechas se pasan como parámetros de URL: ejemplo: 2025-06-10T10:00:00
	 *
	 * @param pistaId ID de la pista a consultar
	 * @param inici   fecha y hora de inicio del rango
	 * @param fi      fecha y hora de fin del rango
	 * @return lista de reservas en ese rango con HTTP 200
	 */
	@GetMapping("/disponibilitat")
	@Operation(summary = "Consultar disponibilitat d'una pista", description = "Retorna les reserves existents d'una pista en un rang de temps.")
	public ResponseEntity<List<Reserva>> getDisponibilitat(@RequestParam String pistaId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inici,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fi) {
		return ResponseEntity.ok(reservaService.getDisponibilitatPista(pistaId, inici, fi));
	}

	/**
	 * PUT /api/reserves/{id}/cancellar
	 *
	 * Cancela una reserva.
	 *
	 * @param id          ID de la reserva a cancelar
	 * @param userDetails datos del usuario autenticado
	 * @return la reserva con estado CANCELLED y HTTP 200
	 */
	@PutMapping("/{id}/cancellar")
	@Operation(summary = "Cancel·lar una reserva", description = "Només el creador o un admin pot cancel·lar la reserva.")
	public ResponseEntity<Reserva> cancellar(@PathVariable String id,
			@AuthenticationPrincipal UserDetails userDetails) {
		String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
		return ResponseEntity.ok(reservaService.cancellarReserva(id, usuariId));
	}
}
