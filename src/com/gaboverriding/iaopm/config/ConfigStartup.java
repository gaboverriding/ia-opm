package com.gaboverriding.iaopm.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.gaboverriding.iaopm.model.Articulo;
import com.gaboverriding.iaopm.model.Proyecto;
import com.googlecode.objectify.ObjectifyService;

public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Articulo.class);
		ObjectifyService.register(Proyecto.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

}
