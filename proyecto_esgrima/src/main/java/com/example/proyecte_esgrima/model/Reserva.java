package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.proyecte_esgrima.model.enums.EstadoReserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "reserves")
public class Reserva {

    @Id
    private String id;

    private LocalDateTime data;
    
    private LocalDateTime dataHoraInici;
    private LocalDateTime dataHoraFi;

    // ID referencia de la pista
    private String pistaId;     
    // IDs de usuarios
    private String esgrimista1Id;
    private String esgrimista2Id;

    // Completada, Cancelada
    private EstadoReserva estat;
    
    public boolean buscarRivalAutomatic;


    public Reserva() {
        this.data = LocalDateTime.now();
        this.estat = EstadoReserva.PENDING;
    }
    
    // Getters i Setters

    


	public String getPistaId() {
		return pistaId;
	}

	public boolean isBuscarRivalAutomatic() {
		return buscarRivalAutomatic;
	}

	public void setBuscarRivalAutomatic(boolean buscarRivalAutomatic) {
		this.buscarRivalAutomatic = buscarRivalAutomatic;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public void setPistaId(String pistaId) {
		this.pistaId = pistaId;
	}

	
	public String getEsgrimista1Id() {
		return esgrimista1Id;
	}

	public void setEsgrimista1Id(String esgrimista1Id) {
		this.esgrimista1Id = esgrimista1Id;
	}

	public String getEsgrimista2Id() {
		return esgrimista2Id;
	}

	public void setEsgrimista2Id(String esgrimista2Id) {
		this.esgrimista2Id = esgrimista2Id;
	}

	public EstadoReserva getEstat() {
		return estat;
	}

	public void setEstat(EstadoReserva estat) {
		this.estat = estat;
	}

	public LocalDateTime getDataHoraInici() {
		return dataHoraInici;
	}

	public void setDataHoraInici(LocalDateTime dataHoraInici) {
		this.dataHoraInici = dataHoraInici;
	}

	public LocalDateTime getDataHoraFi() {
		return dataHoraFi;
	}

	public void setDataHoraFi(LocalDateTime dataHoraFi) {
		this.dataHoraFi = dataHoraFi;
	}
	
	
  
}
