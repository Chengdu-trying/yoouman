package com.yoouman.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContext implements ApplicationContextAware{ 

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext ac) 
	throws BeansException { 
	// TODO Auto-generated method stub 
	          MyApplicationContext.applicationContext=ac; 
	} 
	public  static ApplicationContext  getApplicationContext(){ 
	
	return   applicationContext; 
	//String[]   str=new   String[4]; 
	
	}
}

