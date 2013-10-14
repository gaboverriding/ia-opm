package com.gaboverriding.iaopm.dao;

import java.util.List;

import com.gaboverriding.iaopm.model.Proyecto;

public interface ProyectoDAO {

	
	String save(Proyecto proyecto);

	List<Proyecto> getAll();

	Boolean remove(Proyecto proyecto);


	Proyecto findById(Long id);


}
