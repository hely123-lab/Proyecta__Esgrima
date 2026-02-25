package com.example.proyecte_esgrima.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Document que representa l'usuari de l'aplicació implementa userDetails per
 * el login amb security
 */
@Document(collection = "usuaris")
public class Usuari implements UserDetails {

    @Id
    private String id;

    private String nom;
    private String cognoms;

    @Indexed(unique = true)
    private String email;

    private String password;

    private LocalDate dataNaixement;
    private String sexe;

    private NivelEsgrimista nivell;

    private Role rol;
    private boolean actiu = true;

    //Constructors

    public Usuari() {}
    
    

    public Usuari(String nom, String cognoms, String email, String password,LocalDate dataNaixement, String sexe, NivelEsgrimista nivell,  Role rol) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.password = password;
        this.dataNaixement = dataNaixement;
        this.sexe = sexe;
        this.nivell = nivell;
        this.rol = rol;
        this.actiu = true;
    }

    //UserDetails
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired()   { return true;  }
    @Override
    public boolean isAccountNonLocked()    { return actiu; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled()             { return actiu; }



    //Getters i Setters
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

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public boolean isActiu() {
		return actiu;
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}


    
	
}
