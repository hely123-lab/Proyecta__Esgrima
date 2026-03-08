package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;

/**
 * Document que representa la pista.
 */
@Document(collection = "pistes")
public class PistaCombate {

	/*
	 * Id unica del document en MongoDB
	 */
	@Id
	private String id;

	/*
	 * Nombre de la PistaCombate ej: PistaCombate A, PistaCombate B etc..
	 */
	private String nom;

	/*
	 * Tipo de deporte asociada a la pista. en nuestro caso Esgrima
	 */
	private String tipusEsport;

	/*
	 * Descripció adicional de la pista.
	 */
	private String descripcio;

	/*
	 * Tipos de armas para la pista (ESPADA, FLORETE o SABLE).
	 */
	private ArmaEsgrima tipusArma;

	/*
	 * Indica si la pista està disponible para ser reservada.
	 */
	private boolean disponible = true;

	/*
	 * Constructor vacio
	 */
	public PistaCombate() {
	}

	/*
	 * Constructor con parametros.
	 */
	public PistaCombate(String nom, String descripcio, ArmaEsgrima tipusArma) {
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipusArma = tipusArma;
		this.disponible = true;
	}

	// Getters i Setters
	public String getNom() {
		return nom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTipusEsport() {
		return tipusEsport;
	}

	public void setTipusEsport(String tipusEsport) {
		this.tipusEsport = tipusEsport;
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

	public void setTipusArma(ArmaEsgrima tipusArma) {
		this.tipusArma = tipusArma;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
