package com.mochila.gen.dto;

import java.util.Date;

import com.mochila.inv.dto.Producto;

import java.util.Date;

/*
* @author Jarryo
* @name GenInteraccion.java
*/

public class Interaccion {

	Long secuencia;
	Producto producto;
	Usuario usuarioCreacion;
	String descripcion;
	String referencia;
	Date fechaCreacion;

	public Interaccion() {
		super();
	}

	public Interaccion(Long secuencia, Producto producto, Usuario usuarioCreacion, String descripcion,
			String referencia, Date fechaCreacion) {
		this.secuencia = secuencia;
		this.producto = producto;
		this.usuarioCreacion = usuarioCreacion;
		this.descripcion = descripcion;
		this.referencia = referencia;
		this.fechaCreacion = fechaCreacion;
	}
	
	
	

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return " Interaccion [" + (!(secuencia + "").equals(null) ? secuencia + " , " : "")
				+ (!(producto.getSecuencia() + "").equals(null) ? producto.getSecuencia() + " , " : "")
				+ (!(usuarioCreacion.getSecuencia() + "").equals(null) ? usuarioCreacion.getSecuencia() + " , " : "")
				+ (descripcion != null ? descripcion + " , " : "") + (referencia != null ? referencia + " , " : "")
				+ (fechaCreacion != null ? fechaCreacion + " , " : "")

				+ "]";
	}

}