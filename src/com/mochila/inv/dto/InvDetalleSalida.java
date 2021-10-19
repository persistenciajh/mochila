package com.mochila.inv.dto;

public class InvDetalleSalida {

	Long secuencia;
	InvSalida  secuenciaIngreso ;
	Producto  secuenciaProducto;
	int  cantidad ;
	Double  precio ;
	
	public InvDetalleSalida() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public InvDetalleSalida(Long secuencia, InvSalida secuencia_ingreso, Producto secuencia_producto, int cantidad,
			Double precio) {
		super();
		this.secuencia = secuencia;
		this.secuenciaIngreso = secuencia_ingreso;
		this.secuenciaProducto = secuencia_producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}



	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public InvSalida getSecuencia_ingreso() {
		return secuenciaIngreso;
	}

	public void setSecuencia_ingreso(InvSalida secuencia_ingreso) {
		this.secuenciaIngreso = secuencia_ingreso;
	}

	public Producto getSecuencia_producto() {
		return secuenciaProducto;
	}

	public void setSecuencia_producto(Producto secuencia_producto) {
		this.secuenciaProducto = secuencia_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
}
