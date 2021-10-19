package com.mochila.framework.helper;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppContext {

	private static Logger log = Logger.getLogger(AppContext.class);
	
	public static final AppContext appContext = new AppContext();
	private String contextPath;
	private Properties appProperties;
	private Map appParameters;
	
	public AppContext(){
		log.info("AppContext Initialized...");
	}
	
	public static AppContext getInstance(){
		return appContext;
	}

	public String getContextPath() {
		log.info("Ejecutando el método [ getContextPath() ]...");
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		log.info("setContextPath( " + contextPath + ")");
		this.contextPath = contextPath;
	}
	
	public Properties getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}

	public Map getAppParameters() {
		return appParameters;
	}

	public void setAppParameters(Map appParameters) {
		this.appParameters = appParameters;
	}
	
	public Object getAppParameter(String parameter){		
		return this.appParameters.get(parameter);
	}
}
