package com.mochila.framework.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import com.mochila.framework.util.MyBatisUtil;
import org.apache.log4j.Logger;

public class MyBatisUtil {
	/** Creates a new instance of Configuracion */
	public static final MyBatisUtil pConfiguradorIbatis = new MyBatisUtil();

	public static Logger log = Logger.getLogger(MyBatisUtil.class);

	/**
	 * ibatis
	 */
	public SqlSessionFactory sqlSessionFactory;

	MyBatisUtil() {

		super();

	}
	
	/**
	 * @return
	 */
	public static MyBatisUtil getInstance() {
		log.info("getInstance");
		return pConfiguradorIbatis;
	}
	
	//anterior 
	private String resource = "mybatis-config.xml";
	private SqlSession session = null;

	public SqlSession getSession() {
		System.out.println("Ejecutando [getSession]... ");

		try {
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
			session = sqlSessionFactory.openSession();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("¡¡SqlSession configurado OK!!");
		return session;
	}
	
	//nuevo
	/**
	 * configura ibatis para el acceso a la BD
	 * 
	 * @throws IOException
	 */
	public void configurar() throws IOException {
		log.info("configurar");
//		log.debug("ambiente ==> " + ambiente);
		
//		String resource = "mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);//,ambiente);

		log.trace("SqlSession configurado OK!! ");

	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
