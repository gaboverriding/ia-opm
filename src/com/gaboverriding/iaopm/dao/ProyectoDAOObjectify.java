package com.gaboverriding.iaopm.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import com.gaboverriding.iaopm.model.Proyecto;
import com.googlecode.objectify.Key;


public class ProyectoDAOObjectify implements Serializable, ProyectoDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String save(Proyecto proyecto) {
		ofy().save().entity(proyecto).now();
		return proyecto.getId();
	}

	@Override
	public List<Proyecto> getAll() {
		return ofy().load().type(Proyecto.class).list();
	}

	@Override
	public Boolean remove(Proyecto proyecto) {
		ofy().delete().entity(proyecto).now();
		return true;
	}

	@Override
	public Proyecto findById(Long id) {
		Key<Proyecto> k = Key.create(Proyecto.class, id);
		return ofy().load().key(k).get();
	}

}
