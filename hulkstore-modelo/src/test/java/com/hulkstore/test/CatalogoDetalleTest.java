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
import com.hulkstore.modelo.CatalogoDetalle;
import com.hulkstore.modelo.dao.CatalogoDao;
import com.hulkstore.modelo.dao.CatalogoDetalleDao;
import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */
@RunWith(Arquillian.class)
public class CatalogoDetalleTest {

	private static final String DETALLE_ALIAS_PADRE = "TIPO_ARTICULO";
	private static final String DETALLE_ALIAS = "VESTIMENTA";
	private static final String DETALLE_NOMBRE = "CAMISETA HOMBRE";
	private static final String DETALLE_DESCRIPCION = "Camiseta para hombre";
	private static final String CLIENTE = "127.0.0.1";
	private static final String USUARIO_AUDITORIA = "edison.agurto";
	private static final Integer ID_CATALOGO_REFERENCIA = 2;
	private static final String ALIAS_MODIFICADO = "CAMISETA";

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "catalogoDetalle-test.war")
				.addPackage(CatalogoDetalle.class.getPackage()).addPackage(CatalogoDetalleDao.class.getPackage())
				.addPackage(Catalogo.class.getPackage()).addPackage(CatalogoDao.class.getPackage())
				.addPackage(DaoGenericoUtil.class.getPackage()).addPackage(AuditoriaBaseUtilModelo.class.getPackage())
				.addAsResource("META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	CatalogoDetalleDao catalogoDetalleDao;

	@Test
	public void pruebaPersistenciaCatalogoDetalle() {

		CatalogoDetalle catalogoDetalle = new CatalogoDetalle();
		asignarDataPrueba(catalogoDetalle);

		catalogoDetalleDao.crear(catalogoDetalle);
		CatalogoDetalle catalogoDetalleObtenido = catalogoDetalleDao.obtenerPorId(catalogoDetalle.getId());
		Assert.assertNotNull(catalogoDetalleObtenido.getId());
		Assert.assertEquals(DETALLE_ALIAS, catalogoDetalleObtenido.getAliasDetalle());
		Assert.assertEquals(DETALLE_NOMBRE, catalogoDetalleObtenido.getNombre());
		Assert.assertEquals(DETALLE_DESCRIPCION, catalogoDetalleObtenido.getDescripcion());
		Assert.assertEquals(Boolean.TRUE, catalogoDetalleObtenido.getEstado());
		Assert.assertEquals(CLIENTE, catalogoDetalleObtenido.getCliente());
		Assert.assertNotNull(catalogoDetalleObtenido.getFechaModificacion());
		Assert.assertEquals(USUARIO_AUDITORIA, catalogoDetalleObtenido.getUsuarioModificacion());

		catalogoDetalleObtenido.setAliasDetalle(ALIAS_MODIFICADO);

		catalogoDetalleDao.actualizar(catalogoDetalleObtenido);
		CatalogoDetalle catalogoDetalleModificado = catalogoDetalleDao.obtenerPorId(catalogoDetalleObtenido.getId());

		Assert.assertEquals(ALIAS_MODIFICADO, catalogoDetalleModificado.getAliasDetalle());

		catalogoDetalleDao.eliminar(catalogoDetalleObtenido);
		CatalogoDetalle catalogoDetalleEliminado = catalogoDetalleDao.obtenerPorId(catalogoDetalleModificado.getId());
		Assert.assertNull(catalogoDetalleEliminado);
	}

	private void asignarDataPrueba(CatalogoDetalle catalogoDetalle) {
		Catalogo catalogo = new Catalogo();
		catalogo.setAlias(DETALLE_ALIAS_PADRE);
		catalogo.setIdCatalogo(ID_CATALOGO_REFERENCIA);

		catalogoDetalle.setAliasDetalle(DETALLE_ALIAS);
		catalogoDetalle.setNombre(DETALLE_NOMBRE);
		catalogoDetalle.setDescripcion(DETALLE_DESCRIPCION);
		catalogoDetalle.setCatalogo(catalogo);
		catalogoDetalle.setEstado(Boolean.TRUE);
		catalogoDetalle.setFechaModificacion(new Date());
		catalogoDetalle.setUsuarioModificacion(USUARIO_AUDITORIA);
		catalogoDetalle.setCliente(CLIENTE);

	}

}
