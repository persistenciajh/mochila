package com.mochila.inv.dto;

import java.util.Date;

import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;

import java.util.Date;

/*
* @author Jarryo
* @name InvBodega.java
*/

public class Bodega {

	Long secuencia;
	Persona persona;
	String codigo;
	String nombre;
	String direccion;
	String telefono;
	String estado;
	String descripcion;
	Date fechaCreacion;
	Date fechaActualizacion;
	Usuario usuarioCreacion;
	Usuario usuarioActualizacion;

	public Bodega() {
		super();
	}

	public Bodega(Long secuencia, Persona persona, String codigo, String nombre, String direccion, String telefono,
			String estado, String descripcion, Date fechaCreacion, Date fechaActualizacion,
			Usuario usuarioCreacion, Usuario usuarioActualizacion) {
		this.secuencia = secuencia;
		this.persona = persona;
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = estado;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		return " Bodega [" + (!(secuencia + "").equals(null) ? secuencia + " , " : "")
		// +( !( persona.getSecuencia() +"").equals(null)?
		// persona.getSecuencia()+ " , " : "" )
				+ (codigo != null ? codigo + " , " : "") + (nombre != null ? nombre + " , " : "")
				+ (direccion != null ? direccion + " , " : "") + (telefono != null ? telefono + " , " : "")
				+ (estado != null ? estado + " , " : "") + (descripcion != null ? descripcion + " , " : "")
				+ (fechaCreacion != null ? fechaCreacion + " , " : "")
				+ (fechaActualizacion != null ? fechaActualizacion + " , " : "")
				// +( !( usuarioCreacion.getSecuencia() +"").equals(null)?
				// usuarioCreacion.getSecuencia()+ " , " : "" )
				// +( !( usuarioActualizacion.getSecuencia() +"").equals(null)?
				// usuarioActualizacion.getSecuencia()+ " , " : "" )

				+ "]";
	}

}