package com.gaboverriding.iaopm.dao;

import java.util.List;
import com.gaboverriding.iaopm.model.Articulo;

public interface ArticuloDAO {



	Long save(Articulo articulo);

	List<Articulo> getAll();

	Boolean remove(Articulo articulo);


	Articulo findById(Long id);


}