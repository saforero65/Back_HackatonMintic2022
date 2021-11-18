package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empresarecolectora")
public class EmpresaRecolectora {
	
	@Id
	private String id;
	@NotNull
	private String idSolicitud;
	public EmpresaRecolectora(@NotNull String idSolicitud) {
		super();
		this.idSolicitud = idSolicitud;
	}
	public EmpresaRecolectora(String id, @NotNull String idSolicitud) {
		super();
		this.id = id;
		this.idSolicitud = idSolicitud;
	}
	public EmpresaRecolectora() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	
	

}
