package com.example.proyecte_esgrima.model.dto;

import com.example.proyecte_esgrima.model.NivelEsgrimista;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class RegisterRequest {
    @NotBlank(message = "El nom és obligatori")
    private String nom;

    @NotBlank(message = "Els cognoms són obligatoris")
    private String cognoms;

    @NotBlank(message = "L'email és obligatori")
    @Email(message = "Format d'email invàlid")
    private String email;

    @NotBlank(message = "La contrasenya és obligatòria")
    @Size(min = 6, message = "La contrasenya ha de tenir mínim 6 caràcters")
    private String password;

    @NotNull(message = "La data de naixement és obligatòria")
    @Past(message = "La data de naixement ha de ser al passat")
    private LocalDate dataNaixement;

    @NotBlank(message = "El sexe és obligatori")
    private String sexe;

    @NotNull(message = "El nivell és obligatori")
    private NivelEsgrimista nivell;


    public RegisterRequest() {}

    //Getters i Setters

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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDate getDataNaixement() {
		return dataNaixement;
	}


	public void setDataNaixement(LocalDate dataNaixement) {
		this.dataNaixement = dataNaixement;
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


	public void setNivell(NivelEsgrimista nivell) {
		this.nivell = nivell;
	}
    
    
}
