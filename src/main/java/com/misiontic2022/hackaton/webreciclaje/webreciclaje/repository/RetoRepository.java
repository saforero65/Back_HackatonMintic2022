package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Reto;

public interface RetoRepository extends MongoRepository<Reto, String>  {
	
	List<Reto> findAll ();
	Reto save(Reto reto);
		
}
