package com.example.proyecte_esgrima.controller;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.dto.ReservaRequest;
import com.example.proyecte_esgrima.services.ReservaService;
import com.example.proyecte_esgrima.services.UsuariService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Gestió de reserves de pistes de combat.
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
        this.usuariService  = usuariService;
    }

    @PostMapping
    @Operation(summary = "Crear una reserva de combat",
               description = "Crea una reserva per a dos esgrimistes concrets o activa la cerca automàtica de rival.")
    public ResponseEntity<Reserva> crear(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ReservaRequest request) {
        String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.crearReserva(usuariId, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir una reserva per ID")
    public ResponseEntity<Reserva> getById(@PathVariable String id) {
        return ResponseEntity.ok(reservaService.getById(id));
    }

    @GetMapping("/meves")
    @Operation(summary = "Historial de les meves reserves",
               description = "Retorna totes les reserves de l'usuari autenticat.")
    public ResponseEntity<List<Reserva>> getMevesReserves(
            @AuthenticationPrincipal UserDetails userDetails) {
        String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(reservaService.getHistorialByUsuari(usuariId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Llistar totes les reserves (ADMIN)")
    public ResponseEntity<List<Reserva>> getAll() {
        return ResponseEntity.ok(reservaService.getAll());
    }

    @GetMapping("/disponibilitat")
    @Operation(summary = "Consultar disponibilitat d'una pista",
               description = "Retorna les reserves existents d'una pista en un rang de temps.")
    public ResponseEntity<List<Reserva>> getDisponibilitat(
            @RequestParam String pistaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inici,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fi) {
        return ResponseEntity.ok(reservaService.getDisponibilitatPista(pistaId, inici, fi));
    }

    @PutMapping("/{id}/cancellar")
    @Operation(summary = "Cancel·lar una reserva",
               description = "Només el creador o un admin pot cancel·lar la reserva.")
    public ResponseEntity<Reserva> cancellar(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        String usuariId = usuariService.getPerfilByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(reservaService.cancellarReserva(id, usuariId));
    }
}
