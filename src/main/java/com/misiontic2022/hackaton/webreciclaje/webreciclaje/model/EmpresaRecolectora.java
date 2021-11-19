package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empresarecolectora")
public class EmpresaRecolectora {
	
	@Id
	private String id;
	@NotNull
	private String idSolicitud;
	
	
    @Size(min = 5, max = 200, message 
    	      = "Default")
	private String nombreEmpresa;
	
	private String miembros;

	public EmpresaRecolectora(@NotNull String idSolicitud,
			@Size(min = 5, max = 200, message = "Default") String nombreEmpresa, String miembros) {
		super();
		this.idSolicitud = idSolicitud;
		this.nombreEmpresa = nombreEmpresa;
		this.miembros = miembros;
	}

	public EmpresaRecolectora() {
		super();
	}

	public EmpresaRecolectora(String id) {
		super();
		this.id = id;
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

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getMiembros() {
		return miembros;
	}

	public void setMiembros(String miembros) {
		this.miembros = miembros;
	}
	
	
	

	
	

}
