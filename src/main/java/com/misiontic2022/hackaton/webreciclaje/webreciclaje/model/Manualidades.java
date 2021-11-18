package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "manualidades")
public class Manualidades {
	
	
	@Id
	private String id;
	private String idUsuario;
	
	@Size(min = 7, max = 200, message 
	  	      = "Default")
	private String descripcion;
	private String imagen;
	
	
	public Manualidades(String id, String idUsuario, @Size(min = 7, max = 200, message = "Default") String descripcion,
			String imagen) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
	public Manualidades() {
		super();
	}
	public Manualidades(String idUsuario, @Size(min = 7, max = 200, message = "Default") String descripcion,
			String imagen) {
		super();
		this.idUsuario = idUsuario;
		this.descripcion = descripcion;
		this.imagen = imagen;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	

	
	

}
