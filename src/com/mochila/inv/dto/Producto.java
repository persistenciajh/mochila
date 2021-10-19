package com.mochila.inv.dto;

import java.util.Date;

import com.mochila.gen.dto.Usuario;

/*
* @author Jarryo
* @name InvProducto.java
*/

public class Producto {

	Long secuencia;
	TipoProducto tipoProducto;
	String codigo;
	String nombre;
	Double precioVenta;
	Double ultPrecioCompra;
	Long cantidadMin;
	Long descuentoMax;
	String estado;
	String descripcion;
	Date fechaCreacion;
	Date fechaActualizacion;
	Usuario usuarioCreacion;
	Usuario usuarioActualizacion;

	public Producto() {
		super();
	}

	public Producto(Long secuencia, TipoProducto tipoProducto, String codigo, String nombre, Double precioVenta,
			Double ultPrecioCompra, Long cantidadMin, Long descuentoMax, String estado, String descripcion,
			Date fechaCreacion, Date fechaActualizacion, Usuario usuarioCreacion, Usuario usuarioActualizacion) {
		this.secuencia = secuencia;
		this.tipoProducto = tipoProducto;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.ultPrecioCompra = ultPrecioCompra;
		this.cantidadMin = cantidadMin;
		this.descuentoMax = descuentoMax;
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

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
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

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Double getUltPrecioCompra() {
		return ultPrecioCompra;
	}

	public void setUltPrecioCompra(Double ultPrecioCompra) {
		this.ultPrecioCompra = ultPrecioCompra;
	}

	public Long getCantidadMin() {
		return cantidadMin;
	}

	public void setCantidadMin(Long cantidadMin) {
		this.cantidadMin = cantidadMin;
	}

	public Long getDescuentoMax() {
		return descuentoMax;
	}

	public void setDescuentoMax(Long descuentoMax) {
		this.descuentoMax = descuentoMax;
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
		return " Producto [" + (!(secuencia + "").equals(null) ? secuencia + " , " : "")
		// +( !( tipoProducto.getSecuencia() +"").equals(null)?
		// tipoProducto.getSecuencia()+ " , " : "" )
				+ (codigo != null ? codigo + " , " : "") + (nombre != null ? nombre + " , " : "")
				+ (precioVenta != null ? precioVenta + " , " : "")
				+ (ultPrecioCompra != null ? ultPrecioCompra + " , " : "")
				+ (!(cantidadMin + "").equals(null) ? cantidadMin + " , " : "")
				+ (!(descuentoMax + "").equals(null) ? descuentoMax + " , " : "")
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