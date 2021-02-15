package com.hulkstore.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hulkstore.modelo.Producto;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class ProductoDao extends DaoGenericoUtil<Producto> {

	@PersistenceContext(unitName = "hulkStorePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProductoDao() {
		super(Producto.class);
	}

}
