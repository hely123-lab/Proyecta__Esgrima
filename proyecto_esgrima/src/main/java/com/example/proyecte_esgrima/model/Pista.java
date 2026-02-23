package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pistes")
public class Pista {

	@Id
	private String id;

	private String nom;
	private String tipusEsport;

	public Pista() {
	}

	public Pista(String nom, String tipusEsport) {
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
