package com.hulkstore.modelo.utils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class DaoGenericoUtil<T> {

	@Resource
	private SessionContext sessionContext;

	private Class<T> claseEntidad;

	protected abstract EntityManager getEntityManager();

	public DaoGenericoUtil(Class<T> entityClass) {
		this.claseEntidad = entityClass;
	}

	public T crear(T entity) {
		asignarValoresAuditoria(entity);
		getEntityManager().persist(entity);
		getEntityManager().flush();
		getEntityManager().refresh(entity);
		return entity;
	}

	public T actualizar(T entity) {
		asignarValoresAuditoria(entity);
		return getEntityManager().merge(entity);
	}

	public void eliminar(T entity) {
		asignarValoresAuditoria(entity);
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T obtenerPorId(Object id) {
		return getEntityManager().find(claseEntidad, id);
	}

	private void asignarValoresAuditoria(T entity) {
		if (entity instanceof AuditoriaBaseUtilModelo) {
			AuditoriaBaseUtilModelo auditoriaUtilModelo = (AuditoriaBaseUtilModelo) entity;
			auditoriaUtilModelo.setFechaModificacion(new Date());

			// guardar la ip
			// UsuarioPrincipalDTO principalSeguridad = (UsuarioPrincipalDTO)
			// sessionContext.getCallerPrincipal();
			// auditoriaUtilModelo.setHostWeb(principalSeguridad.getIpIngreso());
			// se obtiene usuario autenticado
			if (null == auditoriaUtilModelo.getUsuarioModificacion()) {
				auditoriaUtilModelo.setUsuarioModificacion(sessionContext.getCallerPrincipal().getName());
			}
		}
	}

	protected String obtenerUsuario() {
		return sessionContext.getCallerPrincipal().getName();
	}

	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(claseEntidad));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(claseEntidad));
		Query query = getEntityManager().createQuery(cq);
		query.setMaxResults(range[1] - range[0] + 1);
		query.setFirstResult(range[0]);
		return query.getResultList();
	}

	public int count() {
		CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = criteriaQuery.from(claseEntidad);
		criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query query = getEntityManager().createQuery(criteriaQuery);
		return ((Long) query.getSingleResult()).intValue();
	}

}