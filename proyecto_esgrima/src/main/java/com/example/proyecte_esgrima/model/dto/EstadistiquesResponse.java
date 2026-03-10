package com.example.proyecte_esgrima.model.dto;

/**
 * 
 * DTO de salida con las estadisticas basicas de un esgrimista.
 * 
 */
public class EstadistiquesResponse {

	private String usuariId;
	private long totalCombats;
	private long victories;
	private long derrotes;
	// Porcentage calculado vicoruas / totalCombates * 100
	private double percentatgeVictories;

	public EstadistiquesResponse() {
	}

	public EstadistiquesResponse(String usuariId, long totalCombats, long victories) {
		this.usuariId = usuariId;
		this.totalCombats = totalCombats;
		this.victories = victories;
		this.derrotes = totalCombats - victories;
		// Evitamos divisiones por zero si no ha jugado ningun combate.
		this.percentatgeVictories = totalCombats > 0 ? Math.round((victories * 100.0 / totalCombats) * 10.0) / 10.0
				: 0.0;
	}

	// Getters y Setters
	public String getUsuariId() {
		return usuariId;
	}

	public void setUsuariId(String usuariId) {
		this.usuariId = usuariId;
	}

	public long getTotalCombats() {
		return totalCombats;
	}

	public void setTotalCombats(long totalCombats) {
		this.totalCombats = totalCombats;
	}

	public long getVictories() {
		return victories;
	}

	public void setVictories(long victories) {
		this.victories = victories;
	}

	public long getDerrotes() {
		return derrotes;
	}

	public void setDerrotes(long derrotes) {
		this.derrotes = derrotes;
	}

	public double getPercentatgeVictories() {
		return percentatgeVictories;
	}

	public void setPercentatgeVictories(double p) {
		this.percentatgeVictories = p;
	}
}