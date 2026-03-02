package com.example.proyecte_esgrima.services.impl;


import com.example.proyecte_esgrima.model.PistaCombate;
import com.example.proyecte_esgrima.model.Reserva;
import com.example.proyecte_esgrima.model.dto.ReservaRequest;
import com.example.proyecte_esgrima.model.enums.EstadoReserva;
import com.example.proyecte_esgrima.repository.PistaRepository;
import com.example.proyecte_esgrima.repository.ReservaRepository;
import com.example.proyecte_esgrima.repository.UsuariRepository;
import com.example.proyecte_esgrima.services.ReservaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuariRepository usuariRepository;
    private final PistaRepository pistaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                               UsuariRepository usuariRepository,
                               PistaRepository pistaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuariRepository  = usuariRepository;
        this.pistaRepository   = pistaRepository;
    }

    @Override
    public Reserva crearReserva(String esgrimista1Id, ReservaRequest request) {
        // Validar esgrimista 1
        usuariRepository.findById(esgrimista1Id)
                .orElseThrow(() -> new Exception("Esgrimista"));

        // Validar pista
        Pista pista = pistaRepository.findById(request.getPistaId())
                .orElseThrow(() -> new Exception("Pista de combat"));

        // Validar rang horari
        if (!request.getDataHoraFi().isAfter(request.getDataHoraInici())) {
            throw new Exception("La data/hora de fi ha de ser posterior a la d'inici");
        }

        // Validar disponibilitat de la pista (sense solapaments)
        Optional<Reserva> solapament = reservaRepository
                .findByPistaIdAndEstatNotAndDataHoraIniciLessThanAndDataHoraFiGreaterThan(
                        request.getPistaId(),
                        EstadoReserva.CANCELLED,
                        request.getDataHoraFi(),
                        request.getDataHoraInici()
                );
        if (solapament.isPresent()) {
            throw new Exception("La pista ja està reservada en el rang horari indicat");
        }

        Reserva reserva = new Reserva();
        reserva.setPistaId(request.getPistaId());
        reserva.setEsgrimista1Id(esgrimista1Id);
        reserva.setDataHoraInici(request.getDataHoraInici());
        reserva.setDataHoraFi(request.getDataHoraFi());
        reserva.setTipusArma(request.getTipusArma());
        reserva.setBuscarRivalAutomatic(request.isBuscarRivalAutomatic());

        if (request.isBuscarRivalAutomatic()) {
            assignarRivalAutomatic(reserva, esgrimista1Id, request);
        } else {
            if (request.getEsgrimista2Id() == null || request.getEsgrimista2Id().isBlank()) {
                throw new Exception(
                        "Cal indicar l'ID del segon esgrimista o activar la cerca automàtica de rival");
            }
            if (request.getEsgrimista2Id().equals(esgrimista1Id)) {
                throw new Exception("Els dos esgrimistes han de ser persones diferents");
            }
            usuariRepository.findById(request.getEsgrimista2Id())
                    .orElseThrow(() -> new Exception("Esgrimista 2"));
            reserva.setEsgrimista2Id(request.getEsgrimista2Id());
            reserva.setEstat(EstadoReserva.CONFIRMED);
        }

        reserva = reservaRepository.save(reserva);
        return reserva;
    }

    /** Cerca una reserva PENDING compatible i confirma les dues si en troba una. */
    private void assignarRivalAutomatic(Reserva novaReserva, String esgrimista1Id,
                                         ReservaRequest request) {
        List<Reserva> candidats = reservaRepository
                .findByEstatAndBuscarRivalAutomaticAndTipusArma(
                        EstadoReserva.PENDING, true, request.getTipusArma());

        Optional<Reserva> candidat = candidats.stream()
                .filter(r -> !r.getEsgrimista1Id().equals(esgrimista1Id))
                .filter(r -> r.getDataHoraInici().equals(request.getDataHoraInici()))
                .findFirst();

        if (candidat.isPresent()) {
            Reserva reservaExistent = candidat.get();
            reservaExistent.setEsgrimista2Id(esgrimista1Id);
            reservaExistent.setEstat(EstadoReserva.CONFIRMED);
            reservaRepository.save(reservaExistent);
            novaReserva.setEsgrimista2Id(reservaExistent.getEsgrimista1Id());
            novaReserva.setEstat(EstadoReserva.CONFIRMED);
        } else {
            novaReserva.setEstat(EstadoReserva.PENDING);
        }
    }

    @Override
    public Reserva getById(String id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva"));
        Pista pista = pistaRepository.findById(reserva.getPistaId()).orElse(null);
        return reserva;
    }

    @Override
    public List<Reserva> getHistorialByUsuari(String usuariId) {
        List<Reserva> totes = new ArrayList<>(reservaRepository.findByEsgrimista1Id(usuariId));
        totes.addAll(reservaRepository.findByEsgrimista2Id(usuariId));
        return totes;
    }

    @Override
    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> getDisponibilitatPista(String pistaId,
                                                         LocalDateTime inici, LocalDateTime fi) {
        return reservaRepository.findByPistaIdAndDataHoraIniciiBetween(pistaId, inici, fi);
    }

    @Override
    public Reserva cancellarReserva(String id, String usuariId) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva"));
        if (!reserva.getEsgrimista1Id().equals(usuariId)) {
            throw new Exception("Només el creador de la reserva pot cancel·lar-la");
        }
        if (reserva.getEstat() == EstadoReserva.COMPLETED) {
            throw new Exception("No es pot cancel·lar una reserva ja completada");
        }
        if (reserva.getEstat() == EstadoReserva.CANCELLED) {
            throw new Exception("Aquesta reserva ja estava cancel·lada");
        }
        reserva.setEstat(EstadoReserva.CANCELLED);
        reservaRepository.save(reserva);
        Pista pista = pistaRepository.findById(reserva.getPistaId()).orElse(null);
        return reserva;
    }


    private String getNomUsuari(String id) {
        if (id == null) return null;
        return usuariRepository.findById(id)
                .map(u -> u.getNom() + " " + u.getCognoms())
                .orElse("Usuari desconegut");
    }
}
