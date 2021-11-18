package com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Reto;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.RetoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reto/")
public class RetoController {
	
	@Autowired
	RetoRepository retoRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Reto>> getAllRetos() {
		List<Reto> response =  retoRepository.findAll();
		if (!response.isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
	return new ResponseEntity("no se encontraron retos",HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping
	public ResponseEntity<Reto> newReto (@Valid @RequestBody Reto reto, BindingResult bindingResult){
		
		return new ResponseEntity("no se encontraron retos",HttpStatus.BAD_REQUEST);
	}

}
