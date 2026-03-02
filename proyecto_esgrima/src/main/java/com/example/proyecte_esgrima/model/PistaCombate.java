package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	 * Constructor vacio
	 */
	public PistaCombate() {
	}

	/*
	 * Constructor con parametros.
	 */
	public PistaCombate(String nom, String tipusEsport) {
		this.setNom(nom);
		this.setTipusEsport(tipusEsport);
	}

	// Getters i Setters
	public String getNom() {
		return nom;
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
}
