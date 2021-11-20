package com.misiontic2022.hackaton.webreciclaje.webreciclaje;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping("/dashboard")
	public String viewHomePage() {
		return "dash.html";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		
		return "login";
	}
}
