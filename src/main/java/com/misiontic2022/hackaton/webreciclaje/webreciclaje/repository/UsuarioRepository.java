package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;



public interface UsuarioRepository extends MongoRepository<Usuario, String>{
	
	List<Usuario> findByEmail(String email);
	
	List<Usuario> findByNombrecompleto(String nombrecompleto);


}
