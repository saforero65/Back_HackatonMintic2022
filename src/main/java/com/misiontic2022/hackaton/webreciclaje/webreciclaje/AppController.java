package com.misiontic2022.hackaton.webreciclaje.webreciclaje;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.JwtDto;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.security.token.JwtProvider;

@Controller
public class AppController {
    @Autowired
    private JwtProvider jwtProvider;
	
    
    @GetMapping("/error.html")
    public String redError () {
    	return "login.html";
    	
    }
    
	@GetMapping("/")
	public String viewIndex () {
		return "login.html";
	
	}
	
	
	@GetMapping("/dashboard")
	public String viewHomePage(@RequestHeader String token) {
		System.out.println(token);
		if (jwtProvider.validateToken(token)) {
			return "/dash.html";
		}
		return "login.html";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		
		return "login.html";
	}
}
