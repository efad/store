package com.hulkstore.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hulkstore.modelo.CatalogoDetalle;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class CatalogoDetalleDao extends DaoGenericoUtil<CatalogoDetalle> {

	@PersistenceContext(unitName = "hulkStorePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CatalogoDetalleDao() {
		super(CatalogoDetalle.class);
	}

}
