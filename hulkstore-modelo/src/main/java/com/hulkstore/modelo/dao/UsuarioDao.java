package com.hulkstore.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hulkstore.modelo.Usuario;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class UsuarioDao extends DaoGenericoUtil<Usuario> {

	@PersistenceContext(unitName = "hulkStorePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario obtenerUsuario(String usuarioLogin, String contrasenia) {
		Usuario usuario;

		StringBuilder consulta = new StringBuilder();
		consulta.append(" SELECT c ");
		consulta.append(" FROM Usuario c ");
		consulta.append(" WHERE  ");
		consulta.append("  c.usuarioLogin = :usuarioLogin ");
		consulta.append("  AND c.contrasenia = :contrasenia ");
		consulta.append("  AND c.estado = :estado ");

		TypedQuery<Usuario> controlDatos = em.createQuery(consulta.toString(), Usuario.class);

		controlDatos.setParameter("usuarioLogin", usuarioLogin);
		controlDatos.setParameter("contrasenia", contrasenia);
		controlDatos.setParameter("estado", Boolean.TRUE);
		try {
			usuario = controlDatos.getSingleResult();
			if (null != usuario) {
				return usuario;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
