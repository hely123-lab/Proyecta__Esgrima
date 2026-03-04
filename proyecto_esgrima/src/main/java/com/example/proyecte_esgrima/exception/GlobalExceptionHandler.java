package com.example.proyecte_esgrima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestor Global de Exceptions
 *
 * Con RestCOntrollerAdvice recogemos todas las excepciones que se lanzan desde
 * cualquier controlador y las gestionamos devolviendo una respuesta HTTP con el
 * codigo correcto.
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(RecursNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(ReglaNegociException.class)
	public ResponseEntity<ErrorResponse> handleReglaNegoci(ReglaNegociException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse("S'ha produït un error inesperat: " + ex.getMessage()));
	}
}
