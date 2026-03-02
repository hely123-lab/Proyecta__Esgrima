package com.example.proyecte_esgrima.services;

import java.util.List;

import com.example.proyecte_esgrima.model.dto.PistaCombateRequest;
import com.example.proyecte_esgrima.model.dto.PistaCombateResponse;

public interface PistaCombateService {
	PistaCombateResponse crear(PistaCombateRequest request);

	PistaCombateResponse getById(String id);

	List<PistaCombateResponse> getAll();

	List<PistaCombateResponse> getDisponibles();

	PistaCombateResponse update(String id, PistaCombateRequest request);

	void delete(String id);
}