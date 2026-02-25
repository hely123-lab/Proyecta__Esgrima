package com.example.proyecte_esgrima.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "L'email és obligatori")
    @Email(message = "Format d'email invàlid")
    private String email;

    @NotBlank(message = "La contrasenya és obligatòria")
    private String password;

    public LoginRequest() {}

    //Getters i Setters
    
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
    
    
}
