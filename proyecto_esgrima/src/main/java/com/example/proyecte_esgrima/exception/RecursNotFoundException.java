package com.example.proyecte_esgrima.exception;

/*
 * Exception que se lanza cuando no se encuentra un recurso en la base de dades.
 * Equivalente a un error HTTP 404 Not Foud.
 * 
 * */
public class RecursNotFoundException extends RuntimeException {
	private final String recurs;
	private final String identificador;

	public RecursNotFoundException(String recurs, String identificador) {
		super(recurs + " no trobat/da amb id: " + identificador);
		this.recurs = recurs;
		this.identificador = identificador;
	}

	public String getRecurs() {
		return recurs;
	}

	public String getIdentificador() {
		return identificador;
	}
}
