package com.example.proyecte_esgrima.model.dto;

import com.example.proyecte_esgrima.model.enums.ArmaEsgrima;
import com.example.proyecte_esgrima.model.enums.NivelEsgrimista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class UpdateUsuariRequest {
	@NotBlank(message = "El nom és obligatori")
	private String nom;

	@NotBlank(message = "Els cognoms són obligatoris")
	private String cognoms;

	@NotNull(message = "La data de naixement és obligatòria")
	@Past(message = "La data de naixement ha de ser al passat")
	private LocalDate dataNaixement;

	@NotBlank(message = "El sexe és obligatori")
	private String sexe;

	@NotNull(message = "El nivell és obligatori")
	private NivelEsgrimista nivell;

	@NotNull(message = "L'arma preferida és obligatòria")
	private ArmaEsgrima armaPreferida;

	public UpdateUsuariRequest() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public LocalDate getDataNaixement() {
		return dataNaixement;
	}

	public void setDataNaixement(LocalDate d) {
		this.dataNaixement = d;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public NivelEsgrimista getNivell() {
		return nivell;
	}

	public void setNivell(NivelEsgrimista n) {
		this.nivell = n;
	}

	public ArmaEsgrima getArmaPreferida() {
		return armaPreferida;
	}

	public void setArmaPreferida(ArmaEsgrima a) {
		this.armaPreferida = a;
	}
}
