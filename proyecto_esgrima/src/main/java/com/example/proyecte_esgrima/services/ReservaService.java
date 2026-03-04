package com.example.proyecte_esgrima.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.dto.ReservaRequest;

public interface ReservaService {

	Reserva crearReserva(String esgrimista1Id, ReservaRequest request) throws Exception;

	Reserva getById(String id) throws Exception;

	List<Reserva> getHistorialByUsuari(String usuariId);

	List<Reserva> getAll();

	List<Reserva> getDisponibilitatPista(String pistaId, LocalDateTime inici, LocalDateTime fi);

	Reserva cancellarReserva(String id, String usuariId) throws Exception;

}
