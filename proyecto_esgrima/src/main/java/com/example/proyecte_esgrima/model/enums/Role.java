package com.example.proyecte_esgrima.model.enums;

/*
 * Enumeracion que define los roles disponibles en el sistema.
 * 
 * La utilizamos con Spring Security para 
 * gestionar la autorizacion de los usuarios
 * 
 * ROLE_USER --> Usuario estandar (esgrimista)
 * ROLE_ADMIN --> Administrador del sistema
 * 
 */
public enum Role {

	/*
	 * Rol de usuario normal. Puede hacer acciones basicas como reservas o
	 * consultas.
	 */
	ROLE_USER,

	/*
	 * Rol de Administrador. tiene permisos elevados para gestioonar usuarios,
	 * pistas, reservas y resultados.
	 */
	ROLE_ADMIN

}
