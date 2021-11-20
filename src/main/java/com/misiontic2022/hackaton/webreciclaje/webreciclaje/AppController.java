package com.misiontic2022.hackaton.webreciclaje.webreciclaje;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.security.token.JwtProvider;

@Controller
public class AppController {
    @Autowired
    private JwtProvider jwtProvider;
	

    
	@GetMapping("/")
	public String viewIndex () {
		return "/login.html";
	}
	

//	@GetMapping("/dash.html")
//	public String viewHomePage() {
//
//		return "../static/dash.html";
//		
//		
//	}
	
	@GetMapping("/auth")
	public  ResponseEntity<?>viewHomePage(@RequestHeader(value="Authorization") String token) {
		if (jwtProvider.validateToken(token)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
		
	
	@GetMapping("/login")	
	public String viewLoginPage() {
		
		return "/login.html";
	}
}
