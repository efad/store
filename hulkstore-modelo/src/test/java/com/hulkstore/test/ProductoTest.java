package com.hulkstore.test;

import java.math.BigDecimal;
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

import com.hulkstore.modelo.Producto;
import com.hulkstore.modelo.dao.ProductoDao;
import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@RunWith(Arquillian.class)
public class ProductoTest {

	private static final String NOMBRE = "Batitaza";
	private static final String NOMBRE_MODIFICADO = "Bati Taza Oficial";
	private static final String DESCRIPCION = "Batitaza negra de metal";
	private static final BigDecimal VALOR_UNITARIO = new BigDecimal(20.15D).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	private static final Integer EXISTENCIA = 15;
	private static final String CLIENTE = "127.0.0.1";
	private static final String USUARIO = "edison.agurto";

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "producto-test.war").addPackage(Producto.class.getPackage())
				.addPackage(ProductoDao.class.getPackage()).addPackage(DaoGenericoUtil.class.getPackage())
				.addPackage(AuditoriaBaseUtilModelo.class.getPackage()).addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	ProductoDao productoDao;

	@Test
	public void pruebaPersistenciaProducto() {

		Producto producto = new Producto();
		producto.setNombre(NOMBRE);
		producto.setDescripcion(DESCRIPCION);
		producto.setEstado(Boolean.TRUE);
		producto.setValor(VALOR_UNITARIO);
		producto.setExistencia(EXISTENCIA);
		producto.setCliente(CLIENTE);
		producto.setFechaModificacion(new Date());
		producto.setUsuarioModificacion(USUARIO);

		productoDao.crear(producto);
		Producto productoObtenido = productoDao.obtenerPorId(producto.getId());
		Assert.assertEquals(NOMBRE, productoObtenido.getNombre());
		Assert.assertEquals(DESCRIPCION, productoObtenido.getDescripcion());
		Assert.assertEquals(Boolean.TRUE, productoObtenido.getEstado());
		Assert.assertEquals(VALOR_UNITARIO, productoObtenido.getValor());
		Assert.assertEquals(EXISTENCIA, productoObtenido.getExistencia());
		Assert.assertEquals(CLIENTE, productoObtenido.getCliente());
		Assert.assertNotNull(productoObtenido.getFechaModificacion());
		Assert.assertEquals(USUARIO, productoObtenido.getUsuarioModificacion());

		productoObtenido.setNombre(NOMBRE_MODIFICADO);

		productoDao.actualizar(productoObtenido);
		Producto productoModificado = productoDao.obtenerPorId(productoObtenido.getId());
		Assert.assertEquals(NOMBRE_MODIFICADO, productoModificado.getNombre());

		productoDao.eliminar(productoObtenido);
		Producto productoEliminado = productoDao.obtenerPorId(productoModificado.getId());
		Assert.assertNull(productoEliminado);
	}

}
