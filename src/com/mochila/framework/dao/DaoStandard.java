/**
 * 
 */
package com.mochila.framework.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.mochila.framework.util.MyBatisUtil;;

public abstract class DaoStandard implements IDao {

	public SqlSessionFactory sqlSessionFactory = MyBatisUtil
			.getInstance().getSqlSessionFactory();
	public Logger log = Logger.getLogger(this.getClass());

	public DaoStandard() {
		super();
	}

}
