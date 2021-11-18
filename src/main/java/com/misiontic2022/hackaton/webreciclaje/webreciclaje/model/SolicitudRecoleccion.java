package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "solicitudrecoleccion")
public class SolicitudRecoleccion {
	
	@Id
	private String id;
	@Size(min = 7, max = 200, message 
  	      = "Default")
	private String producto;
	
	@Min(value = 1)
	private String cantidad;
	@Size(min = 7, max = 200, message 
	  	      = "Default")
	private String direccion;
	@NotNull
	private Boolean estado;
	public SolicitudRecoleccion() {
		super();
	}
	public SolicitudRecoleccion(String id, @Size(min = 7, max = 200, message = "Default") String producto,
			@Min(1) String cantidad, @Size(min = 7, max = 200, message = "Default") String direccion,
			@NotNull Boolean estado) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.direccion = direccion;
		this.estado = estado;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
	
}
