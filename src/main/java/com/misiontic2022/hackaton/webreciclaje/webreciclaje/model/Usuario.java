package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
	
	
	@Id
	private String id;
	

	private String nombrecompleto;
	private String password;
	private String email;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Usuario( String password, String nombrecompleto, String email) {
		super();
		
		this.password = password;
		this.nombrecompleto = nombrecompleto;
		this.email = email;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre_completo() {
		return nombrecompleto;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombrecompleto = nombre_completo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
