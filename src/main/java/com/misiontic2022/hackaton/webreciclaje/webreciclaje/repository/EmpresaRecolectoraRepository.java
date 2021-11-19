package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.EmpresaRecolectora;

public interface EmpresaRecolectoraRepository extends MongoRepository<EmpresaRecolectora, String> {

	
	List<EmpresaRecolectora> findAll();
	Optional<EmpresaRecolectora> findById(String id);
	
}
