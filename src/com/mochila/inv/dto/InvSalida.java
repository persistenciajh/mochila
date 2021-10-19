package com.mochila.inv.dto;

import java.util.Date;

import com.mochila.gen.dto.Persona;

public class InvSalida {

	  Long secuencia;
	  Persona secuenciaPersona;
	  Long secuenciaUsuario;
	  String codigo;
	  String numero;
	  String tipo;
	  Date fecha;
	  Double total;
	  String estado;
	  String descripcion;
	  int descuento;
	  int impuesto;
	  
	  Date fechaCreacion;
	  
	  
	  
	  
	public InvSalida() {
		super();
		
		// TODO Auto-generated constructor stub
	}


	public InvSalida(Long secuencia, Persona secuenciaPersona, Long secuenciaUsuario, String codigo, String numero,
			String tipo, Date fecha, Double total, String estado, String descripcion,int descuento,int impuesto,Date fechaCreacion) {
		super();
		this.secuencia = secuencia;
		this.secuenciaPersona = secuenciaPersona;
		this.secuenciaUsuario = secuenciaUsuario;
		this.codigo = codigo;
		this.numero = numero;
		this.tipo = tipo;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.descripcion = descripcion;
		this.descuento= descuento;
		this.impuesto=impuesto;
		this.fechaCreacion=fechaCreacion;
	}


	public Long getSecuencia() {
		return secuencia;
	}


	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}


	public Persona getSecuenciaPersona() {
		return secuenciaPersona;
	}


	public void setSecuenciaPersona(Persona secuenciaPersona) {
		this.secuenciaPersona = secuenciaPersona;
	}


	public Long getSecuenciaUsuario() {
		return secuenciaUsuario;
	}


	public void setSecuenciaUsuario(Long secuenciaUsuario) {
		this.secuenciaUsuario = secuenciaUsuario;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
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


	public int getDescuento() {
		return descuento;
	}


	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}


	public int getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}	
	
	
	
	
}
