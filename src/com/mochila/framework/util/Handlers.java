package com.mochila.framework.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class Handlers {
	
	public List buscar(String nombreConsulta,String parametro) {
		List lista= new ArrayList();
		System.out.println("metodo [buscar]");
		

		try {
			SqlSession session = new MyBatisUtil().getSession();
			if (session != null) {
				try {
					lista = session.selectList(nombreConsulta,parametro);

					System.out.println("Hay " + lista.size() + " datos encontrados.");
				} finally {
					session.close();
				}
			} else {
				System.out.println("Session NULL...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
