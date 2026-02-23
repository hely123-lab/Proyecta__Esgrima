package com.example.proyecte_esgrima.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "reserves")
public class Reserva {

    @Id
    private String id;

    private LocalDate data;
    private LocalTime hora;

    // ID referencia de la pista
    private String pistaId;     
    // IDs de usuarios
    private List<String> usuarisIds; 

    // Completada, Cancelada
    private String estat;
    public Reserva() {}

    public Reserva(LocalDate data, LocalTime hora,
                   String pistaId, List<String> usuarisIds, String estat) {
        this.setData(data);
        this.setHora(hora);
        this.setPistaId(pistaId);
        this.setUsuarisIds(usuarisIds);
        this.setEstat(estat);
    }
    
    // Getters i Setters

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getPistaId() {
		return pistaId;
	}

	public void setPistaId(String pistaId) {
		this.pistaId = pistaId;
	}

	public List<String> getUsuarisIds() {
		return usuarisIds;
	}

	public void setUsuarisIds(List<String> usuarisIds) {
		this.usuarisIds = usuarisIds;
	}

	public String getEstat() {
		return estat;
	}

	public void setEstat(String estat) {
		this.estat = estat;
	}
  
}
