package com.gaboverriding.iaopm.config;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(){
		String prefix = getServletContext().getRealPath("/");
		String archivo = getInitParameter("log4j-init-file");
		//System.out.println("prefix:" + prefix);
		System.out.println("archivo de configuracion log4j:" + prefix + archivo + ":");
		// if the log4j-init-file is not set, then no point in trying
		if(archivo != null) {
			PropertyConfigurator.configure(prefix + archivo);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
	}

}
