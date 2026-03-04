package com.example.proyecte_esgrima.model.dto;

public class AuthResponse {
    private String token;
    private String email;
    private String rol;
    private String nom;

    public AuthResponse() {}
    public AuthResponse(String token, String email, String rol, String nom) {
        this.token = token;
        this.email = email;
        this.rol = rol;
        this.nom = nom;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
   
    
}
