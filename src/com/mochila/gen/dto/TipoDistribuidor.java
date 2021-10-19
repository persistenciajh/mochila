package com.mochila.gen.dto;

public class TipoDistribuidor {

	Long secuencia;
	String nombre;
	String estado;
	String descripcion;

	public TipoDistribuidor() {
	}

	public TipoDistribuidor(Long secuencia, String nombre, String estado, String descripcion) {
		super();
		this.secuencia = secuencia;
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEstado() {
		return estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
