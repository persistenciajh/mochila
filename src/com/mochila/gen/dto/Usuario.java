package com.mochila.gen.dto;

import java.util.Date;

/*
* @author Jarryo
* @name GenUsuario.java
*/

public class Usuario {

	Long secuencia;
	Persona persona;
	Long grupo;
	String nombreUsuario;
	String correoElectronico;
	String clave;
	String estado;
	String descripcion;
	Date fechaCreacion;
	Date fechaActualizacion;
	Usuario usuarioCreacion;
	Usuario usuarioActualizacion;

	public Usuario() {
		super();
	}

	public Usuario(Long secuencia, Persona persona, Long grupo, String nombreUsuario, String correoElectronico,
			String clave, String estado, String descripcion, Date fechaCreacion, Date fechaActualizacion,
			Usuario usuarioCreacion, Usuario usuarioActualizacion) {
		this.secuencia = secuencia;
		this.persona = persona;
		this.grupo = grupo;
		this.nombreUsuario = nombreUsuario;
		this.correoElectronico = correoElectronico;
		this.clave = clave;
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

	public Long getGrupo() {
		return grupo;
	}

	public void setGrupo(Long grupo) {
		this.grupo = grupo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
		return " Usuario [" + (!(secuencia + "").equals(null) ? secuencia + " , " : "")
//				+ (!(persona.getSecuencia() + "").equals(null) ? persona.getSecuencia() + " , " : "")
//				+ (!(persona.getSecuencia() + "").equals(null) ? persona.getSecuencia() + " , " : "")
				+ (nombreUsuario != null ? nombreUsuario + " , " : "")
				+ (correoElectronico != null ? correoElectronico + " , " : "") + (clave != null ? clave + " , " : "")
				+ (estado != null ? estado + " , " : "") + (descripcion != null ? descripcion + " , " : "")
				+ (fechaCreacion != null ? fechaCreacion + " , " : "")
				+ (fechaActualizacion != null ? fechaActualizacion + " , " : "")
//				+ (!(usuarioCreacion.getSecuencia() + "").equals(null) ? usuarioCreacion.getSecuencia() + " , " : "")
//				+ (!(usuarioActualizacion.getSecuencia() + "").equals(null)
//						? usuarioActualizacion.getSecuencia() + " , " : "")

				+ "]";
	}

}