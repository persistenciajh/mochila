package com.mochila.inv.dto;
 import java.util.Date;

import com.mochila.gen.dto.Usuario;

/*
* @author Jarryo
* @name InvFotoProducto.java
*/

public class FotoProducto{

Long secuencia;
Producto producto;
byte img[];
String estado;
String descripcion;
Date fechaCreacion;
Date fechaActualizacion;
Usuario usuarioCreacion;
Usuario usuarioActualizacion;
public FotoProducto() {
super();
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


public byte[] getImg() {
	return img;
}


public void setImg(byte[] img) {
	this.img = img;
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
 return " FotoProducto [" 
+( !( secuencia +"").equals(null)?  secuencia+ " , "  : "" ) 
//+( !( producto.getSecuencia() +"").equals(null)?  producto.getSecuencia()+ " , "  : "" ) 
+(  img != null ? img+ " , "  : "" ) 
+(  estado != null ? estado+ " , "  : "" ) 
+(  descripcion != null ? descripcion+ " , "  : "" ) 
+(  fechaCreacion != null ? fechaCreacion+ " , "  : "" ) 
+(  fechaActualizacion != null ? fechaActualizacion+ " , "  : "" ) 
//+( !( usuarioCreacion.getSecuencia() +"").equals(null)?  usuarioCreacion.getSecuencia()+ " , "  : "" ) 
//+( !( usuarioActualizacion.getSecuencia() +"").equals(null)?  usuarioActualizacion.getSecuencia()+ " , "  : "" ) 

 + "]"; }
 
}