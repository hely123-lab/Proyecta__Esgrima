package com.example.proyecte_esgrima.services;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.dto.ReservaRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaService {
    Reserva crearReserva(String esgrimista1Id, ReservaRequest request);
    Reserva getById(String id) throws Exception;
    List<Reserva> getHistorialByUsuari(String usuariId);
    List<Reserva> getAll();
    List<Reserva> getDisponibilitatPista(String pistaId, LocalDateTime inici, LocalDateTime fi);
    Reserva cancellarReserva(String id, String usuariId) throws Exception;
}
