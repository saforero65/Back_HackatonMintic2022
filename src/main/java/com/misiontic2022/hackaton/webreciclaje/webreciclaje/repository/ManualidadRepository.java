package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Manualidad;

public interface ManualidadRepository extends MongoRepository<Manualidad, String > {
	
	List<Manualidad> findAll();
	Manualidad save(Manualidad manualidad);
	Optional<Manualidad> findById(String id);
	
	
	

}
