package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reto")
public class Reto {
	
	@Id
	private String id;
	
	private String idUsuario;
	
	@Size(min = 30, max = 800, message 
	  	      = "Default")
	private String descripcion;
	
	private boolean cumplido;

	public Reto(String id, String idUsuario, @Size(min = 30, max = 800, message = "Default") String descripcion,
			boolean cumplido) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.descripcion = descripcion;
		this.cumplido = cumplido;
	}

	public Reto(String idUsuario, @Size(min = 30, max = 800, message = "Default") String descripcion,
			boolean cumplido) {
		super();
		this.idUsuario = idUsuario;
		this.descripcion = descripcion;
		this.cumplido = cumplido;
	}

	public Reto() {
		super();
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isCumplido() {
		return cumplido;
	}

	public void setCumplido(boolean cumplido) {
		this.cumplido = cumplido;
	}
	
	

}
