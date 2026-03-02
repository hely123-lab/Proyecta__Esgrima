package com.example.proyecte_esgrima.model.dto;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

import jakarta.validation.constraints.NotBlank;

/**
 * Clase DTO utilizada para recibir los datos necesarios para crear o actualizar
 * una pista de combate.
 * 
 * Se utiliza en las peticiones HTTP (POST y PUT)
 * 
 */
public class PistaCombateRequest {
	/**
	 * Nombre identificativo de la pista.
	 * 
	 * Es obligatorio y no puede estar vacío.
	 */
	@NotBlank(message = "El nom de la pista és obligatori")
	private String nom;
	/**
	 * Descripción adicional de la pista.
	 * 
	 */
	private String descripcio;
	/**
	 * Tipo de arma permitida en la pista.
	 * 
	 * Se representa mediante el enum ArmaEsgrima (ESPADA, FLORETE o SABLE).
	 */
	private ArmaEsgrima tipusArma;

	/**
	 * Constructor vacío.
	 */
	public PistaCombateRequest() {
	}

	// Getters y Setters
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
}
