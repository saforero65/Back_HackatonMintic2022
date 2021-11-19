package com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Manualidad;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.ManualidadRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/manualidad")
public class ManualidadController {
	@Autowired
	ManualidadRepository manualidadRepository;

	@GetMapping
	public ResponseEntity<List<Manualidad>> getAllManualidades() {
		try {
			List<Manualidad> manualidades = manualidadRepository.findAll();
			if (manualidades.isEmpty()) {
				return new ResponseEntity<List<Manualidad>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(manualidades, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping
	public ResponseEntity<Manualidad> createManualidad(@Valid @RequestBody Manualidad manualidad,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",
					HttpStatus.BAD_REQUEST);
		try {
			Manualidad SaveManualidad = manualidadRepository.save(manualidad);
			return new ResponseEntity<>(SaveManualidad, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Manualidad> updateManualidadById(@PathVariable String id,
			@Valid @RequestBody Manualidad manualidad, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			manualidad.setId(id);
			Manualidad SaveManualidad = manualidadRepository.save(manualidad);
			return new ResponseEntity<>(SaveManualidad, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("No se pudo actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable String id) {
		try {
			manualidadRepository.deleteById(id);
			return new ResponseEntity("reto eliminado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
