package com.mochila.framework.facade;

import java.sql.Connection;

import com.mochila.framework.dao.imp.ParametrizacionDao;

public class ParametrizacionFac {

	private static final ParametrizacionFac parametrizacionFac = new ParametrizacionFac();

	private ParametrizacionFac() {
		super();
	}

	public static ParametrizacionFac getFacade() {
		return parametrizacionFac;
	}

	public Object obtenerListado(String qryName, Object objeto)
			throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		return ParametrizacionDao.obtenerListado(qryName, objeto);
	}

	public Object obtenerListado(String qryName) throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		return ParametrizacionDao.obtenerListado(qryName);
	}

	public Object obtenerRegistro(String qryName, Object objeto)
			throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		return ParametrizacionDao.obtenerRegistro(qryName, objeto);
	}

	public Object obtenerRegistro(String qryName) throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		return ParametrizacionDao.obtenerRegistro(qryName);
	}

	public void guardarRegistro(String qryName, Object objeto) throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		ParametrizacionDao.insertarRegistro(qryName, objeto);
	}

	public void actualizarRegistro(String qryName, Object objeto)
			throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		ParametrizacionDao.actualizarRegistro(qryName, objeto);
	}

	public void borrarRegistro(String qryName, Object objeto) throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		ParametrizacionDao.borrarRegistro(qryName, objeto);
	}

	public void insertarRegistro(String qryName, Object objeto)
			throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		ParametrizacionDao.insertarRegistro(qryName, objeto);
	}

	public Object obtenerListado(String sqlName, Object objeto, int omitir,
			int maximo) throws Exception {
		ParametrizacionDao ParametrizacionDao = new ParametrizacionDao();
		return ParametrizacionDao.obtenerListado(sqlName, objeto, omitir,
				maximo);
	}
	
	public Connection obtenerConexion() {
		ParametrizacionDao parametrizacionDao = new ParametrizacionDao();
		return parametrizacionDao.obtenerConexion();
	}

}