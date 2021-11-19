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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.SolicitudRecoleccion;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.SolicitudRecoleccionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/solirecoleccion")
public class SolicitudRecoleccionController {

	@Autowired
	SolicitudRecoleccionRepository solicitudRecoleccionRepository;

	@GetMapping
	public ResponseEntity<List<SolicitudRecoleccion>> getAllSolicitudesRecoleccion() {
		try {
			List<SolicitudRecoleccion> solicitudes = solicitudRecoleccionRepository.findAll();
			if (solicitudes.isEmpty()) {
				return new ResponseEntity("No se encontraron Solicitudes", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(solicitudes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<SolicitudRecoleccion> getSolicitudRecoleccionById(@PathVariable String id) {
		Optional<SolicitudRecoleccion> solicitud = solicitudRecoleccionRepository.findById(id);
		if (solicitud.isPresent()) {
			return new ResponseEntity<>(solicitud.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSolicitudRecoleccionById(@PathVariable String id) {
		try {
			solicitudRecoleccionRepository.deleteById(id);
			return new ResponseEntity("Solicitud de Recoleccion Eliminada", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping
	public ResponseEntity<SolicitudRecoleccion> newSolicitudRecoleccion(@Valid @RequestBody SolicitudRecoleccion solicitudRecoleccion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			SolicitudRecoleccion solicitudtosave = solicitudRecoleccionRepository.save(solicitudRecoleccion);
			return new ResponseEntity<>(solicitudtosave, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity("No se creo la solicitud, lo siento",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<SolicitudRecoleccion> updateSolicitudRecoleccionById(@PathVariable String id,
			@Valid @RequestBody SolicitudRecoleccion solicitudRecoleccion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			solicitudRecoleccion.setId(id);
			SolicitudRecoleccion saveSolicitudRecoleccion = solicitudRecoleccionRepository.save(solicitudRecoleccion);
			return new ResponseEntity<>(saveSolicitudRecoleccion, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("No se ha actualizado la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
