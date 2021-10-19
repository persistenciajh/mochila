package com.mochila.inv.dto;

import java.util.Date;

import com.mochila.gen.dto.Usuario;

import java.util.Date;

/*
* @author Jarryo
* @name InvTipoProducto.java
*/

public class TipoProducto {

	Long secuencia;
	String nombre;
	String estado;
	String descripcion;
	Date fechaCreacion;
	Date fechaActualizacion;
	Usuario usuarioCreacion;
	Usuario usuarioActualizacion;
	byte img[];

	public TipoProducto() {
		super();
	}

	public TipoProducto(Long secuencia, String nombre, String estado, String descripcion, Date fechaCreacion,
			Date fechaActualizacion, Usuario usuarioCreacion, Usuario usuarioActualizacion) {
		this.secuencia = secuencia;
		this.nombre = nombre;
		this.estado = estado;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioActualizacion = usuarioActualizacion;
	}
	
	

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Usuario getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(Usuario usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	@Override
	public String toString() {
		return " TipoProducto [" + (!(secuencia + "").equals(null) ? secuencia + " , " : "")
				+ (nombre != null ? nombre + " , " : "") + (estado != null ? estado + " , " : "")
				+ (descripcion != null ? descripcion + " , " : "")
				+ (fechaCreacion != null ? fechaCreacion + " , " : "")
				+ (fechaActualizacion != null ? fechaActualizacion + " , " : "")
//				+ (!(usuarioCreacion.getSecuencia() + "").equals(null) ? usuarioCreacion.getSecuencia() + " , " : "")
//				+ (!(usuarioActualizacion.getSecuencia() + "").equals(null)
//						? usuarioActualizacion.getSecuencia() + " , " : "")

				+ "]";
	}

}