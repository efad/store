package com.hulkstore.test;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hulkstore.modelo.Catalogo;
import com.hulkstore.modelo.dao.CatalogoDao;
import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@RunWith(Arquillian.class)
public class CatalogoTest {

	private static final String ALIAS = "TIPO_CUENTA";
	private static final String CLIENTE = "127.0.0.1";
	private static final String USUARIO_AUDITORIA = "edison.agurto";
	private static final String ALIAS_MODIFICADO = "TIPO_PRODUCTO";

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "catalogo-test.war").addPackage(Catalogo.class.getPackage())
				.addPackage(CatalogoDao.class.getPackage()).addPackage(DaoGenericoUtil.class.getPackage())
				.addPackage(AuditoriaBaseUtilModelo.class.getPackage()).addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	CatalogoDao catalogoDao;

	@Test
	public void pruebaPersistenciaCatalogo() {

		Catalogo catalogo = new Catalogo();
		asignarDataPrueba(catalogo);

		catalogoDao.crear(catalogo);
		Catalogo catalogoObtenido = catalogoDao.obtenerPorId(catalogo.getIdCatalogo());
		Assert.assertNotNull(catalogoObtenido.getIdCatalogo());
		Assert.assertEquals(ALIAS, catalogoObtenido.getAlias());
		Assert.assertEquals(Boolean.TRUE, catalogoObtenido.getEstado());
		Assert.assertEquals(CLIENTE, catalogoObtenido.getCliente());
		Assert.assertNotNull(catalogoObtenido.getFechaModificacion());
		Assert.assertEquals(USUARIO_AUDITORIA, catalogoObtenido.getUsuarioModificacion());

		catalogoObtenido.setAlias(ALIAS_MODIFICADO);

		catalogoDao.actualizar(catalogoObtenido);
		Catalogo catalogoModificado = catalogoDao.obtenerPorId(catalogoObtenido.getIdCatalogo());

		Assert.assertEquals(ALIAS_MODIFICADO, catalogoModificado.getAlias());

		catalogoDao.eliminar(catalogoObtenido);
		Catalogo catalogoEliminado = catalogoDao.obtenerPorId(catalogoModificado.getIdCatalogo());
		Assert.assertNull(catalogoEliminado);
	}

	private void asignarDataPrueba(Catalogo catalogo) {
		catalogo.setAlias(ALIAS);
		catalogo.setEstado(Boolean.TRUE);
		catalogo.setFechaModificacion(new Date());
		catalogo.setUsuarioModificacion(USUARIO_AUDITORIA);
		catalogo.setCliente(CLIENTE);

	}

}
