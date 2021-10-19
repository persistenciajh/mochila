package com.mochila.framework.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.mochila.framework.util.MyBatisUtil;
import com.mochila.framework.helper.AppContext;

/**
 * Application Lifecycle Listener implementation class
 * ContextoAplicacionListener
 * 
 * @author jmora
 */
@WebListener
public class ContextoAplicacionListener implements ServletContextListener {

	private static Logger log = Logger
			.getLogger(ContextoAplicacionListener.class);
	private String contextPath;
	private Properties appProperties;
	
	/**
	 * Default constructor.
	 */
	public ContextoAplicacionListener() {
		log.info("ContextoAplicacionListener");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("contextInitialized");
		log.debug("servletContextEvent ==> " + servletContextEvent);
		
		try {
			this.contextPath = servletContextEvent.getServletContext().getRealPath(File.separator);
			this.appProperties = this.getProperties(this.contextPath +File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "Aplicacion.properties");
			
			AppContext appContext = AppContext.getInstance(); 
			appContext.setContextPath(contextPath);
			appContext.setAppProperties(appProperties);
			
			MyBatisUtil.getInstance().configurar(); 					 
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	
    public Properties getProperties(String pathProperties){
    	Properties properties;
	    try {     
	    	FileInputStream file = new FileInputStream(pathProperties);
	        properties = new Properties();
	        properties.load(file);
	        file.close();
	        
	        return properties;
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    	log.error(e);
	    	return null;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	log.error(e);
	    	return null;
	    }
    }	

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		log.info("contextDestroyed");
		log.debug("servletContextEvent ==> " + servletContextEvent);
	}

}
