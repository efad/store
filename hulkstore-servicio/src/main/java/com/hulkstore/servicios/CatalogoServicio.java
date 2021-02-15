package com.hulkstore.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hulkstore.modelo.Catalogo;
import com.hulkstore.modelo.CatalogoDetalle;
import com.hulkstore.modelo.dao.CatalogoDao;
import com.hulkstore.modelo.dao.CatalogoDetalleDao;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class CatalogoServicio {

	@Inject
	private CatalogoDao catalogoDao;

	@Inject
	private CatalogoDetalleDao catalogoDetalleDao;

	public List<Catalogo> obtenerCatalogos() {
		return catalogoDao.findAll();
	}

	public List<CatalogoDetalle> obtenerCatalogoDetalles() {
		return catalogoDetalleDao.findAll();
	}

}
