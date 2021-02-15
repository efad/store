package com.hulkstore.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hulkstore.modelo.Catalogo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class CatalogoDao extends DaoGenericoUtil<Catalogo> {

	@PersistenceContext(unitName = "hulkStorePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CatalogoDao() {
		super(Catalogo.class);
	}

}
