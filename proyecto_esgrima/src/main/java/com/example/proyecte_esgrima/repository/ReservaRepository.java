package com.example.proyecte_esgrima.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;
import com.example.proyecte_esgrima.model.enums.EstadoReserva;

public interface ReservaRepository extends MongoRepository<Reserva, String> {

	List<Reserva> findByEsgrimista1Id(String esgrimista1Id);

	List<Reserva> findByEsgrimista2Id(String esgrimista2Id);

	List<Reserva> findByPistaIdAndDataHoraIniciBetween(String pistaId, LocalDateTime inici, LocalDateTime fi);

	List<Reserva> findByEstatAndBuscarRivalAutomaticAndTipusArma(EstadoReserva estat, boolean buscarRivalAutomatic,
			ArmaEsgrima tipusArma);

	Optional<Reserva> findByPistaIdAndEstatNotAndDataHoraIniciLessThanAndDataHoraFiGreaterThan(String pistaId,
			EstadoReserva estatExclos, LocalDateTime fiNova, LocalDateTime iniciNova);
}
