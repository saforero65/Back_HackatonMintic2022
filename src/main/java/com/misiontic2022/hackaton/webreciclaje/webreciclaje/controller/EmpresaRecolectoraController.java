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
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.EmpresaRecolectora;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.EmpresaRecolectoraRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/empresarecolectora")
public class EmpresaRecolectoraController {

	@Autowired
	EmpresaRecolectoraRepository empresaRecolectoraRepository;

	@GetMapping
	public ResponseEntity<List<EmpresaRecolectora>> getAllEmpresasRecolectoras() {
		List<EmpresaRecolectora> empresasrecolectoras = empresaRecolectoraRepository.finAll();
		if (!empresasrecolectoras.isEmpty()) {
			return new ResponseEntity<>(empresasrecolectoras, HttpStatus.OK);
		}
		return new ResponseEntity("no se encontraron empresas", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpresaRecolectora> getEmpresaByID(@PathVariable String id) {

		Optional<EmpresaRecolectora> empresa = empresaRecolectoraRepository.findById(id);
		if (empresa.isPresent()) {
			return new ResponseEntity<>(empresa.get(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	public ResponseEntity<EmpresaRecolectora> newEmpresaRecolectora(
			@Valid @RequestBody EmpresaRecolectora empresaRecolectora, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			EmpresaRecolectora saveempresaRecolectora = empresaRecolectoraRepository.save(empresaRecolectora);
			return new ResponseEntity<>(saveempresaRecolectora, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("No se creo la empresaRecolectora", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable String id) {
		try {
			empresaRecolectoraRepository.deleteById(id);
			return new ResponseEntity("Empresa Eliminada", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmpresaRecolectora> updateRetoById (@PathVariable String id, @Valid @RequestBody EmpresaRecolectora empresaRecolectora, BindingResult bindingResult){
		if(bindingResult.hasErrors()) 
            return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			empresaRecolectora.setId(id);
			EmpresaRecolectora saveEmpresaRecolectora = empresaRecolectoraRepository.save(empresaRecolectora);
			return new ResponseEntity<>(saveEmpresaRecolectora, HttpStatus.CREATED);
		} catch (Exception e) {

		return new ResponseEntity("No se modifico la empresa, lo siento", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}


