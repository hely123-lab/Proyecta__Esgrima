package com.example.proyecte_esgrima.model.dto;

import java.time.LocalDateTime;

/**
 * DTO de salida con la informacion de un resultado de combate.
 */
public class ResultatCombatResponse {

	private String id;
	private String reservaId;
	private String esgrimista1Id;
	private String esgrimista2Id;
	private int puntsEsgrimista1;
	private int puntsEsgrimista2;
	private String guanyadorId;
	private int duracioMinuts;
	private LocalDateTime dataCombat;

	public ResultatCombatResponse() {
	}

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

	public void setEsgrimista1Id(String e) {
		this.esgrimista1Id = e;
	}

	public String getEsgrimista2Id() {
		return esgrimista2Id;
	}

	public void setEsgrimista2Id(String e) {
		this.esgrimista2Id = e;
	}

	public int getPuntsEsgrimista1() {
		return puntsEsgrimista1;
	}

	public void setPuntsEsgrimista1(int p) {
		this.puntsEsgrimista1 = p;
	}

	public int getPuntsEsgrimista2() {
		return puntsEsgrimista2;
	}

	public void setPuntsEsgrimista2(int p) {
		this.puntsEsgrimista2 = p;
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