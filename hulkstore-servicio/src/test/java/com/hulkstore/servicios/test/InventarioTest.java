package com.hulkstore.servicios.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hulkstore.modelo.Movimiento;
import com.hulkstore.modelo.Producto;
import com.hulkstore.modelo.dao.CatalogoDao;
import com.hulkstore.modelo.dao.MovimientoDao;
import com.hulkstore.modelo.utils.DaoGenericoUtil;
import com.hulkstore.servicios.InventarioServicio;

@RunWith(Arquillian.class)
public class InventarioTest {
	private static final Integer ID_PRODUCTO_REFERENCIA = 19;
	private static final Integer CANTIDAD_REFERENCIA = 5;

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "producto-test.war")
				.addPackage(InventarioServicio.class.getPackage()).addPackage(CatalogoDao.class.getPackage())
				.addPackage(MovimientoDao.class.getPackage()).addPackage(Movimiento.class.getPackage())
				.addAsResource("META-INF/persistence.xml").addPackage(DaoGenericoUtil.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	InventarioServicio inventarioServicio;

	@Test
	public void pruebaPersistenciaProducto() {
		List<Producto> listaProductos = inventarioServicio.obtenerProductos();

		Assert.assertNotNull(inventarioServicio);
		Assert.assertEquals(Boolean.FALSE, listaProductos.isEmpty());
	}

	@Test
	public void probarFuncionalidad() {
		Boolean existencia = inventarioServicio.verificarExistenciaProducto(ID_PRODUCTO_REFERENCIA,
				CANTIDAD_REFERENCIA);

		assertTrue(existencia);
	}
}
