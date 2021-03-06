package com.hulkstore.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hulkstore.modelo.Movimiento;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class MovimientoDao extends DaoGenericoUtil<Movimiento> {

	@PersistenceContext(unitName = "hulkStorePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MovimientoDao() {
		super(Movimiento.class);
	}

}
