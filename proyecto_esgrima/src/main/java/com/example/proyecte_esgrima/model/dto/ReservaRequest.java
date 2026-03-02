package com.example.proyecte_esgrima.model.dto;

import java.time.LocalDateTime;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

import jakarta.validation.constraints.NotNull;

public class ReservaRequest {
   
	@NotNull(message = "L'ID de la pista és obligatori")
    private String pistaId;

    private String esgrimista2Id;

    @NotNull(message = "La data i hora d'inici és obligatòria")
    private LocalDateTime dataHoraInici;

    @NotNull(message = "La data i hora de fi és obligatòria")
    private LocalDateTime dataHoraFi;

    private boolean buscarRivalAutomatic = false;
    
    private ArmaEsgrima tipusArma;

    public ReservaRequest() {}
    
    //Getters i Setters

	public String getPistaId() {
		return pistaId;
	}

	public void setPistaId(String pistaId) {
		this.pistaId = pistaId;
	}

	public String getEsgrimista2Id() {
		return esgrimista2Id;
	}

	public void setEsgrimista2Id(String esgrimista2Id) {
		this.esgrimista2Id = esgrimista2Id;
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

	public boolean isBuscarRivalAutomatic() {
		return buscarRivalAutomatic;
	}

	public void setBuscarRivalAutomatic(boolean buscarRivalAutomatic) {
		this.buscarRivalAutomatic = buscarRivalAutomatic;
	}

	public ArmaEsgrima getTipusArma() {
		return tipusArma;
	}

	public void setTipusArma(ArmaEsgrima tipusArma) {
		this.tipusArma = tipusArma;
	}
	
	
    
  
}
