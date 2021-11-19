package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.SolicitudRecoleccion;

public interface SolicitudRecoleccionRepository extends MongoRepository<SolicitudRecoleccion, String> {

	List<SolicitudRecoleccion> findAll();
	Optional<SolicitudRecoleccion> findById(String id);
	
}
