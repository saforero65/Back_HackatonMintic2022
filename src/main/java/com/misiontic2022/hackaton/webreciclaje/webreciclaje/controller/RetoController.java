package com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Reto;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.RetoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reto")
public class RetoController {

	@Autowired
	RetoRepository retoRepository;

	@GetMapping
	public ResponseEntity<List<Reto>> getAllRetos() {
		List<Reto> response = retoRepository.findAll();
		if (!response.isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity("no se encontraron retos", HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/consulta/{id}")
	public ResponseEntity<Reto> getRetoById (@PathVariable String id){
		
		Optional<Reto> reto = retoRepository.findById(id);
		if (reto.isPresent()) {
			
			return new ResponseEntity<>(reto.get(), HttpStatus.OK);
			
		}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById (@PathVariable String id){
		try {
			retoRepository.deleteById(id);
			return new ResponseEntity("reto eliminado",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping
	public ResponseEntity<Reto> newReto (@Valid @RequestBody Reto reto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) 
            return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			Reto retoSave = retoRepository.save(new Reto (reto.getIdUsuario(),reto.getDescripcion(),reto.isCumplido()));
			return new ResponseEntity<>(retoSave, HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity("No se creo el reto",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
}


