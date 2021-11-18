package com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.UsuarioRepository;



@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String email) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (email == null) {
				usuarioRepository.findAll().forEach(usuarios::add);
			} else {
				usuarioRepository.findByEmail(email).forEach(usuarios::add);
			}

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") String id) {
		Optional<Usuario> usuarioData = usuarioRepository.findById(id);

		if (usuarioData.isPresent()) {
			return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario user, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
            return new ResponseEntity(bindingResult.getAllErrors().toString()+"Please check the fiels", HttpStatus.BAD_REQUEST);
		try {
			Usuario _usuario = usuarioRepository.save(
					new Usuario(passwordEncoder.encode(user.getPassword()),user.getNombrecompleto() ,user.getEmail(),user.getTipo()));
			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") String id, @RequestBody Usuario user) {
		Optional<Usuario> usuarioData = usuarioRepository.findById(id);

		if (usuarioData.isPresent()) {
			Usuario _usuario = usuarioData.get();
			_usuario.setEmail(user.getEmail());
			_usuario.setPassword(user.getPassword());
			return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<HttpStatus> deleteUsuarios(@PathVariable("id") String id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuarios")
	public ResponseEntity<HttpStatus> deleteAllUsuarioss() {
		try {
			usuarioRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/{email}")
	public ResponseEntity<List<Usuario>> findByUsername(@PathVariable("username") String email) {
		try {
			List<Usuario> usuarios = usuarioRepository.findByEmail(email);

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
