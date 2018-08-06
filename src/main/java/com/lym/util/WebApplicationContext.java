package com.lym.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class WebApplicationContext implements ApplicationContextAware{
	
	protected static ApplicationContext appContext;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext app) throws BeansException {
		this.appContext = app;
	}

	public static ApplicationContext getAppContext() {
		return (appContext);
	}
	
	public static Object getBean(String beanName) {
		return appContext.getBean(beanName);
	}


}
