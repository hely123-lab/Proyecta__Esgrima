package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.proyecte_esgrima.model.enums.NivelEsgrimista;
import com.example.proyecte_esgrima.model.enums.Role;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Documento que representa el usuario, que implementa userDetails para ell
 * login con security.
 * 
 */
@Document(collection = "usuaris")
public class Usuari implements UserDetails {
	/*
	 * Id unica del document en MongoDB
	 */
	@Id
	private String id;

	/*
	 * Nombre y Apellido del Usuari
	 */
	private String nom;
	private String cognoms;

	/*
	 * Correo electronico del Usuari Es unico dentro de la collecion
	 */
	@Indexed(unique = true)
	private String email;

	/*
	 * Contraseña del Usuari
	 */
	private String password;

	/*
	 * Fecha de nacimiento y sexo del Usuario
	 */
	private LocalDate dataNaixement;
	private String sexe;

	/*
	 * Nivell esportiu del esgrimista
	 */
	private NivelEsgrimista nivell;

	/*
	 * Rol del usuario dentro del sistema (USER o ADMIN)
	 */
	private Role rol;

	/*
	 * Indica si la cuenta esta activa, Si es fale, el usuario no podra iniciar
	 * sesion.
	 */
	private boolean actiu = true;

	/*
	 * Constructor vacio.
	 */
	public Usuari() {
	}

	/*
	 * Constructor con parametros.
	 */
	public Usuari(String nom, String cognoms, String email, String password, LocalDate dataNaixement, String sexe,
			NivelEsgrimista nivell, Role rol) {
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

	/*
	 * Devuelve los roles del usuario como autoridades de Spring Security
	 * 
	 * @return Llista de autoridades baasda en el rol
	 */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(rol.name()));
	}

	/*
	 * Devuelve el username utilizado por autenticacion En nuestro caso seria el
	 * email.
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/*
	 * Indica si la cuenta no ha expirado.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * Indica si la cuenta no esta bloqueada. con la variable actiu.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return actiu;
	}

	/*
	 * Indica si las credenciales no han expirado.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * Indica si la cuenta esta habilitada
	 */
	@Override
	public boolean isEnabled() {
		return actiu;
	}

	// Getters i Setters Normales
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
