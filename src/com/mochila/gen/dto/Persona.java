package com.mochila.gen.dto;

import java.util.Date;

public class Persona {
	Long secuencia;
    String identificacion; 
    String nombres;
    String apellidos; 
    String correoElectronico;
    String direccion;
    String telefono;
    Date fechaNacimiento;
    String genero;
    String estado ; 
    String descripcion;
    Date fechaCreacion; 
    Date fechaActualizacion;
    Long secUsuarioCreacion;
    Long secUsuarioActualizacion;
    
    String tipoIdentificacion;
    String etiquetaCliente;
    String etiquetaProveedor;
    String etiquetaDistribuidor;
    TipoDistribuidor secuenciaTipoDistribuidor;
    
    Long secuenciaUsuarioCreacion;
    Long secuenciaPersonaReferenciada;
    String wspTelefono;
    
    
    
	
    
    
	public Persona(Long secuencia, String identificacion, String nombres, String apellidos, String correoElectronico,
			String direccion, String telefono, Date fechaNacimiento, String genero, String estado, String descripcion,
			Date fechaCreacion, Date fechaActualizacion, Long secUsuarioCreacion, Long secUsuarioActualizacion,
			 String tipoIdentificacion, String etiquetaCliente,	String etiquetaProveedor, 
			 String etiquetaDistribuidor,TipoDistribuidor secTipoDistribuidor, Long secuenciaUsuarioCreacion,
			 Long secuenciaPersonaReferenciada, String wspTelefono) {
		super();
		this.secuencia = secuencia;
		this.identificacion = identificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correoElectronico = correoElectronico;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.estado = estado;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.secUsuarioCreacion = secUsuarioCreacion;
		this.secUsuarioActualizacion = secUsuarioActualizacion;
		this.secuenciaTipoDistribuidor = secTipoDistribuidor;
		this.tipoIdentificacion = tipoIdentificacion;
		this.etiquetaCliente = etiquetaCliente;
		this.etiquetaProveedor = etiquetaProveedor;
		this.etiquetaDistribuidor = etiquetaDistribuidor;
		
		this.secuenciaUsuarioCreacion=secuenciaUsuarioCreacion;
		this.secuenciaPersonaReferenciada=secuenciaPersonaReferenciada;
		this.wspTelefono=wspTelefono;
	}







	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}







	public Long getSecuencia() {
		return secuencia;
	}







	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}







	public String getIdentificacion() {
		return identificacion;
	}







	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}







	public String getNombres() {
		return nombres;
	}







	public void setNombres(String nombres) {
		this.nombres = nombres;
	}







	public String getApellidos() {
		return apellidos;
	}







	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}







	public String getCorreoElectronico() {
		return correoElectronico;
	}







	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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







	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}







	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}







	public String getGenero() {
		return genero;
	}







	public void setGenero(String genero) {
		this.genero = genero;
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







	public Long getSecUsuarioCreacion() {
		return secUsuarioCreacion;
	}







	public void setSecUsuarioCreacion(Long secUsuarioCreacion) {
		this.secUsuarioCreacion = secUsuarioCreacion;
	}







	public Long getSecUsuarioActualizacion() {
		return secUsuarioActualizacion;
	}







	public void setSecUsuarioActualizacion(Long secUsuarioActualizacion) {
		this.secUsuarioActualizacion = secUsuarioActualizacion;
	}

	public TipoDistribuidor getSecuenciaTipoDistribuidor() {
		return secuenciaTipoDistribuidor;
	}

	public void setSecuenciaTipoDistribuidor(TipoDistribuidor secTipoDistribuidor) {
		this.secuenciaTipoDistribuidor = secTipoDistribuidor;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}







	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}












	public String getEtiquetaCliente() {
		return etiquetaCliente;
	}







	public void setEtiquetaCliente(String etiquetaCliente) {
		this.etiquetaCliente = etiquetaCliente;
	}







	public String getEtiquetaProveedor() {
		return etiquetaProveedor;
	}







	public void setEtiquetaProveedor(String etiquetaProveedor) {
		this.etiquetaProveedor = etiquetaProveedor;
	}







	public String getEtiquetaDistribuidor() {
		return etiquetaDistribuidor;
	}







	public void setEtiquetaDistribuidor(String etiquetaDistribuidor) {
		this.etiquetaDistribuidor = etiquetaDistribuidor;
	}







	public Long getSecuenciaUsuarioCreacion() {
		return secuenciaUsuarioCreacion;
	}







	public void setSecuenciaUsuarioCreacion(Long secuenciaUsuarioCreacion) {
		this.secuenciaUsuarioCreacion = secuenciaUsuarioCreacion;
	}







	public Long getSecuenciaPersonaReferenciada() {
		return secuenciaPersonaReferenciada;
	}







	public void setSecuenciaPersonaReferenciada(Long secuenciaPersonaReferenciada) {
		this.secuenciaPersonaReferenciada = secuenciaPersonaReferenciada;
	}







	public String getWspTelefono() {
		return wspTelefono;
	}







	public void setWspTelefono(String wspTelefono) {
		this.wspTelefono = wspTelefono;
	}







	@Override 
	 public String toString() {
	 return " Persona [" 
	+( !( secuencia +"").equals(null)?  secuencia+ " , "  : "" ) 
	+(  identificacion != null ? identificacion+ " , "  : "" ) 
	+(  nombres != null ? nombres+ " , "  : "" ) 
	+(  apellidos != null ? apellidos+ " , "  : "" ) 
	+(  correoElectronico != null ? correoElectronico+ " , "  : "" ) 
	+(  direccion != null ? direccion+ " , "  : "" ) 
	+(  telefono != null ? telefono+ " , "  : "" ) 
	+(  fechaNacimiento != null ? fechaNacimiento+ " , "  : "" ) 
	+(  genero != null ? genero+ " , "  : "" ) 
	+(  estado != null ? estado+ " , "  : "" ) 
	+(  descripcion != null ? descripcion+ " , "  : "" ) 
	+(  fechaCreacion != null ? fechaCreacion+ " , "  : "" ) 
	+(  fechaActualizacion != null ? fechaActualizacion+ " , "  : "" ) 
	+ "]"; }
    
}
