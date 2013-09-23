package com.gaboverriding.iaopm.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import com.gaboverriding.iaopm.model.Articulo;

import com.googlecode.objectify.Key;

public class ArticuloDAOObjectify implements Serializable, ArticuloDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8337700736834254734L;

	@Override
	public Long save(Articulo articulo) {
		ofy().save().entity(articulo).now();
		return articulo.getId();
	}

	@Override
	public List<Articulo> getAll() {
		return ofy().load().type(Articulo.class).list();
	}

	@Override
	public Boolean remove(Articulo articulo) {
		ofy().delete().entity(articulo).now();
		return true;
	}

	@Override
	public Articulo findById(Long id) {
		Key<Articulo> k = Key.create(Articulo.class, id);
		return ofy().load().key(k).get();
	}

}