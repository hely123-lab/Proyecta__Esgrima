package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuaris")
public class Usuari {

    @Id
    private String id;

    private String nom;

    @Indexed(unique = true)
    private String email;

    private Integer edat;
    private String sexe;
    // Basic, Mitja, Alt
    private String nivell; 
    private String password;
    // Admin, User
    private String rol; 

    public Usuari() {}

    public Usuari(String nom, String email, Integer edat,
                  String sexe, String nivell, String password, String rol) {
        this.setNom(nom);
        this.email = email;
        this.setEdat(edat);
        this.setSexe(sexe);
        this.setNivell(nivell);
        this.setPassword(password);
        this.setRol(rol);
    }

    
    // Getters i Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getEdat() {
		return edat;
	}

	public void setEdat(Integer edat) {
		this.edat = edat;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNivell() {
		return nivell;
	}

	public void setNivell(String nivell) {
		this.nivell = nivell;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
