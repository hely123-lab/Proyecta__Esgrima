package com.example.proyecte_esgrima.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Registra el resultat d'un combat. Vinculat a una Reserva.
 */
@Document(collection = "resultats_combat")
public class ResultatCombat {

	@Id
	private String id;

	private String reservaId;
	private String esgrimista1Id;
	private String esgrimista2Id;

	private int puntsEsgrimista1;
	private int puntsEsgrimista2;

	private String guanyadorId;
	private int duracioMinuts;

	private LocalDateTime dataCombat;

	public ResultatCombat() {
		this.dataCombat = LocalDateTime.now();
	}

	// Getters i setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
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

	public int getPuntsEsgrimista1() {
		return puntsEsgrimista1;
	}

	public void setPuntsEsgrimista1(int puntsEsgrimista1) {
		this.puntsEsgrimista1 = puntsEsgrimista1;
	}

	public int getPuntsEsgrimista2() {
		return puntsEsgrimista2;
	}

	public void setPuntsEsgrimista2(int puntsEsgrimista2) {
		this.puntsEsgrimista2 = puntsEsgrimista2;
	}

	public String getGuanyadorId() {
		return guanyadorId;
	}

	public void setGuanyadorId(String guanyadorId) {
		this.guanyadorId = guanyadorId;
	}

	public int getDuracioMinuts() {
		return duracioMinuts;
	}

	public void setDuracioMinuts(int duracioMinuts) {
		this.duracioMinuts = duracioMinuts;
	}

	public LocalDateTime getDataCombat() {
		return dataCombat;
	}

	public void setDataCombat(LocalDateTime dataCombat) {
		this.dataCombat = dataCombat;
	}

}
