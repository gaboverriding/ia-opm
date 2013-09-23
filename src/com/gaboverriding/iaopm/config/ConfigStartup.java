package com.gaboverriding.iaopm.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.gaboverriding.iaopm.model.Articulo;

import com.googlecode.objectify.ObjectifyService;

public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Articulo.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

}