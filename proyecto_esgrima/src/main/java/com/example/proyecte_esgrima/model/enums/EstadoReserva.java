package com.example.proyecte_esgrima.model.enums;

public enum EstadoReserva {
	PENDING, // Reserva individual esperant rival
	CONFIRMED, // Reserva confirmada con los 2 esgrimista
	COMPLETED, // Cambate finalizado
	CANCELLED // Reserva Cancelada
}
