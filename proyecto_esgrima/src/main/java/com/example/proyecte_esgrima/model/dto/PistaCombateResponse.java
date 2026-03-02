package com.example.proyecte_esgrima.model.dto;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

/**
 * Clase DTO utilizado para enviar al cliente la información de una Pista de
 * Combate.
 * 
 * Esta clase se utiliza en las respuestas HTTP del controlador,
 * 
 */
public class PistaCombateResponse {
	/**
	 * Identificador único de la pista en la base de datos.
	 */
	private String id;
	/**
	 * Nombre identificativo de la pista.
	 */
	private String nom;
	/**
	 * Descripción adicional de la pista. Puede incluir información técnica o de
	 * ubicación.
	 */
	private String descripcio;
	private ArmaEsgrima tipusArma;
	private boolean disponible;

	public PistaCombateResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public ArmaEsgrima getTipusArma() {
		return tipusArma;
	}

	public void setTipusArma(ArmaEsgrima a) {
		this.tipusArma = a;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean d) {
		this.disponible = d;
	}
}
