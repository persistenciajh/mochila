package com.mochila.framework.dao.imp;

import java.sql.Connection;
import java.util.Collection;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mochila.framework.dao.DaoStandard;

public class ParametrizacionDao extends DaoStandard {

	private SqlSession session = sqlSessionFactory.openSession(true);

	@Override
	public Object actualizarRegistro(String sqlName, Object objeto)
			throws Exception {
		try {
			return session.update(sqlName, objeto);
		} finally {
			session.close();
		}

	}

	@Override
	public Object borrarRegistro(String sqlName, Object objeto)
			throws Exception {
		try {
			return session.delete(sqlName, objeto);
		} finally {
			session.close();
		}
	}

	@Override
	public Object insertarRegistro(String sqlName, Object objeto)
			throws Exception {
		try {
			return session.insert(sqlName, objeto);
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Object> obtenerListado(String sqlName) throws Exception {

		try {
			return session.selectList(sqlName);
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Object> obtenerListado(String sqlName, Object objeto)
			throws Exception {
		try {
			return session.selectList(sqlName, objeto);
		} finally {
			session.close();
		}
	}

	@Override
	public Object obtenerRegistro(String sqlName) throws Exception {
		try {
			return session.selectOne(sqlName);
		} finally {
			session.close();
		}
	}

	@Override
	public Object obtenerRegistro(String sqlName, Object objeto)
			throws Exception {
		try {
			return session.selectOne(sqlName, objeto);
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Object> obtenerListado(String sqlName, Object objeto,
			int omitir, int maximo) throws Exception {
		try {
			return session.selectList(sqlName, objeto, new RowBounds(omitir,
					maximo));
		} finally {
			session.close();
		}
	}
	
	@Override
	public Connection obtenerConexion() {
		
		return session.getConnection();
	}

}
