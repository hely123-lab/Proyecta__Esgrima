package com.example.proyecte_esgrima.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;
import com.example.proyecte_esgrima.model.enums.EstadoReserva;

/**
 * Repositorio de la colección "reserves" en MongoDB.
 *
 * Hereda de MongoRepository para las operaciones básicas.
 */
public interface ReservaRepository extends MongoRepository<Reserva, String> {

	List<Reserva> findByEsgrimista1Id(String esgrimista1Id);

	List<Reserva> findByEsgrimista2Id(String esgrimista2Id);

	List<Reserva> findByPistaIdAndDataHoraIniciBetween(String pistaId, LocalDateTime inici, LocalDateTime fi);

	List<Reserva> findByEstatAndBuscarRivalAutomaticAndTipusArma(EstadoReserva estat, boolean buscarRivalAutomatic,
			ArmaEsgrima tipusArma);

	Optional<Reserva> findFirstByPistaIdAndEstatNotAndEsgrimista2IdNotNullAndDataHoraIniciLessThanAndDataHoraFiGreaterThan(
			String pistaId, EstadoReserva estat, LocalDateTime fi, LocalDateTime inici);
}
