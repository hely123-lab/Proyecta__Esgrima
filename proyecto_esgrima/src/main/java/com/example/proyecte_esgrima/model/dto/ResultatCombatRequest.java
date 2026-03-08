package com.example.proyecte_esgrima.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * DTO de entrada para registrar el resultado de un combate.
 * 
 * Se utiliza el endpoint POST /api/resultats.
 */
public class ResultatCombatRequest {

	@NotBlank(message = "L'ID de la reserva es obligatori")
	private String reservaId;

	@NotNull(message = "Els punts de l'esgrimista 1 són obligatoris")
	@Min(value = 0, message = "Els punts no poden ser negatius")
	private int puntsEsgrimista1;

	@NotNull(message = "Els punts de l'esgrimista 2 són obligatoris")
	@Min(value = 0, message = "Els punts no poden ser negatius")
	private int puntsEsgrimista2;

	@Min(value = 1, message = "La duració ha de ser com a mínim 1 minut")
	private int duracioMinuts;

	public ResultatCombatRequest() {
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
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

	public int getDuracioMinuts() {
		return duracioMinuts;
	}

	public void setDuracioMinuts(int duracioMinuts) {
		this.duracioMinuts = duracioMinuts;
	}
}