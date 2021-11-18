package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;
	
	
    @Size(min = 7, max = 200, message 
    	      = "Default")
	private String nombrecompleto;
    @Size(min = 5, max = 200, message 
  	      = "Default")
	private String password;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	@NotBlank
	private String nick;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String tipo;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String password, String nombrecompleto, String email,String tipo) {
		super();

		this.password = password;
		this.nombrecompleto = nombrecompleto;
		this.email = email;
		this.tipo = tipo;
	}

	
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombre_completo) {
		this.nombrecompleto = nombre_completo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
