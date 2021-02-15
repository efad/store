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

import com.hulkstore.modelo.CatalogoDetalle.TipoMovimientoEnum;
import com.hulkstore.modelo.Movimiento;
import com.hulkstore.modelo.Producto;
import com.hulkstore.modelo.Usuario;
import com.hulkstore.modelo.dao.MovimientoDao;
import com.hulkstore.modelo.dao.ProductoDao;
import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@RunWith(Arquillian.class)
public class MovimientoTest {

	private static final String CLIENTE = "127.0.0.1";

	private static final Integer CANTIDAD = 11;
	private static final BigDecimal VALOR_MOVIMIENTO = new BigDecimal(100.15D).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	private static final String USUARIO_AUDITORIA = "edison.agurto";
	private static final Integer ID_PRODUCTO_REFERENCIA = 19;
	private static final Integer ID_USUARIO_REFERENCIA = 10;
	private static final BigDecimal VALOR_MODIFICADO = new BigDecimal(70.15D).setScale(2, BigDecimal.ROUND_HALF_EVEN);

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "movimiento-test.war").addPackage(Movimiento.class.getPackage())
				.addPackage(MovimientoDao.class.getPackage()).addPackage(Producto.class.getPackage())
				.addPackage(ProductoDao.class.getPackage()).addPackage(DaoGenericoUtil.class.getPackage())
				.addPackage(AuditoriaBaseUtilModelo.class.getPackage()).addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	MovimientoDao movimientoDao;

	@Inject
	ProductoDao productoDao;

	@Test
	public void pruebaPersistenciaMovimiento() {

		Movimiento movimiento = new Movimiento();
		asignarDataPrueba(movimiento);

		movimientoDao.crear(movimiento);
		Movimiento movimientoObtenido = movimientoDao.obtenerPorId(movimiento.getId());
		Assert.assertNotNull(movimientoObtenido.getId());
		Assert.assertEquals(CANTIDAD, movimientoObtenido.getCantidad());
		Assert.assertEquals(TipoMovimientoEnum.ENTRADA.name(), movimientoObtenido.getTipoMovimiento());
		Assert.assertEquals(Boolean.TRUE, movimientoObtenido.getEstado());
		Assert.assertEquals(VALOR_MOVIMIENTO, movimientoObtenido.getValor());
		Assert.assertNotNull(movimientoObtenido.getFecha());
		Assert.assertEquals(ID_USUARIO_REFERENCIA, movimientoObtenido.getUsuario().getId());
		Assert.assertEquals(ID_PRODUCTO_REFERENCIA, movimientoObtenido.getProducto().getId());
		Assert.assertEquals(CLIENTE, movimientoObtenido.getCliente());
		Assert.assertNotNull(movimientoObtenido.getFechaModificacion());
		Assert.assertEquals(USUARIO_AUDITORIA, movimientoObtenido.getUsuarioModificacion());

		movimientoObtenido.setValor(VALOR_MODIFICADO);

		movimientoDao.actualizar(movimientoObtenido);
		Movimiento movimientoModificado = movimientoDao.obtenerPorId(movimientoObtenido.getId());

		Assert.assertEquals(VALOR_MODIFICADO, movimientoModificado.getValor());

		movimientoDao.eliminar(movimientoObtenido);
		Movimiento movimientoEliminado = movimientoDao.obtenerPorId(movimientoModificado.getId());
		Assert.assertNull(movimientoEliminado);
	}

	private void asignarDataPrueba(Movimiento movimiento) {

		Usuario usuario = new Usuario();
		usuario.setId(ID_USUARIO_REFERENCIA);

		Producto producto = new Producto();
		producto.setId(ID_PRODUCTO_REFERENCIA);

		movimiento.setCantidad(CANTIDAD);
		movimiento.setTipoMovimiento(TipoMovimientoEnum.ENTRADA.name());
		movimiento.setEstado(Boolean.TRUE);
		movimiento.setValor(VALOR_MOVIMIENTO);
		movimiento.setFecha(new Date());
		movimiento.setUsuario(usuario);
		movimiento.setProducto(producto);
		movimiento.setCliente(CLIENTE);
		movimiento.setFechaModificacion(new Date());
		movimiento.setUsuarioModificacion(USUARIO_AUDITORIA);

	}

}
