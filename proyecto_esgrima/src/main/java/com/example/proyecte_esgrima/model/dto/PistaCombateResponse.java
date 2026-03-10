package com.example.proyecte_esgrima.model.dto;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

/**
 * Clase DTO utilizado para enviar al cliente la información de una Pista de
 * Combate.
 * 
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

	/**
	 * Tipo de arma asociada a la pista. Representado mediante el enum ArmaEsgrima.
	 */
	private ArmaEsgrima tipusArma;

	/**
	 * Indica si la pista está disponible para su uso.
	 * 
	 * true → disponible false → ocupada o no disponible
	 */
	private boolean disponible;

	/*
	 * Constructor vacio.
	 */

	public PistaCombateResponse() {
	}

	// Getters y Setters
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
