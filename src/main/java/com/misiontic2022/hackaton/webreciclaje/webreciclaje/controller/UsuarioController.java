package com.misiontic2022.hackaton.webreciclaje.webreciclaje.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.JwtDto;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.LoginUsuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.UsuarioRepository;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.security.token.JwtProvider;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private JwtProvider jwtProvider;
	



	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

				usuarioRepository.findAll().forEach(usuarios::add);


			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {
		Optional<Usuario> usuarioData = usuarioRepository.findById(id);

		if (usuarioData.isPresent()) {
			return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario user, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
            return new ResponseEntity(bindingResult.getAllErrors().toString()+"Please check the fiels", HttpStatus.BAD_REQUEST);
		
		if (!usuarioRepository.findByNick(user.getNick()).isEmpty()) {
			return new ResponseEntity("Ya hay un nick con dicho nombre", HttpStatus.BAD_REQUEST);
			
		}
		if (!usuarioRepository.findByEmail(user.getEmail()).isEmpty())
			return new ResponseEntity("este email ya esta registrado", HttpStatus.BAD_REQUEST);
		try {
			
			user.addUserRol();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			Usuario _usuario = usuarioRepository.save(user);
					
			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable String id, @RequestBody Usuario user) {
		Optional<Usuario> usuarioData = usuarioRepository.findById(id);

		if (usuarioData.isPresent()) {
			user.setId(id);
			Usuario _usuario = usuarioRepository.save(user);
			
			return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUsuarios(@PathVariable("id") String id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity("usuario eliminado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
	

	@GetMapping("/{email}")
	public ResponseEntity<List<Usuario>> findByEmail(@PathVariable String email) {
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
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUser, BindingResult bindingResult){
		 if(usuarioRepository.findByNick(loginUser.getNick()).isEmpty()){
			 return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		  }	
		Usuario usuario = usuarioRepository.findByNick(loginUser.getNick()).get(0);
 	
	Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNick(), loginUser.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateToken(authentication);
    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    return new ResponseEntity(jwtDto, HttpStatus.OK);
    
	
	}



}