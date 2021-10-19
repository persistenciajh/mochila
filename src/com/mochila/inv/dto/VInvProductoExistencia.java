package com.mochila.inv.dto;

import java.util.Date;

import com.mochila.gen.dto.Usuario;

/*
* @author Jarryo
* @name InvVInvProductoExistencia.java
*/

public class VInvProductoExistencia {



	Producto  producto;
	TipoProducto tipoProducto;
	int entradas;
	int salidas;
	int existencia;
	
	
	public VInvProductoExistencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VInvProductoExistencia(Producto producto, TipoProducto tipoProducto, int entradas, int salidas,
			int existencia) {
		super();
		this.producto = producto;
		this.tipoProducto = tipoProducto;
		this.entradas = entradas;
		this.salidas = salidas;
		this.existencia = existencia;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public int getEntradas() {
		return entradas;
	}

	public void setEntradas(int entradas) {
		this.entradas = entradas;
	}

	public int getSalidas() {
		return salidas;
	}

	public void setSalidas(int salidas) {
		this.salidas = salidas;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	

	}

