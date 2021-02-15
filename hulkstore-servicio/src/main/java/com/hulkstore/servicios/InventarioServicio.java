package com.hulkstore.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hulkstore.modelo.Movimiento;
import com.hulkstore.modelo.Producto;
import com.hulkstore.modelo.dao.MovimientoDao;
import com.hulkstore.modelo.dao.ProductoDao;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class InventarioServicio {

	@Inject
	private MovimientoDao movimientoDao;

	@Inject
	private ProductoDao productoDao;

	public List<Movimiento> obtenerMovimientos() {
		return movimientoDao.findAll();
	}

	public Movimiento crearMovimientos(Movimiento movimiento) {
		return movimientoDao.crear(movimiento);
	}

	public Movimiento actualizarMovimientos(Movimiento movimiento) {
		return movimientoDao.actualizar(movimiento);
	}

	public List<Producto> obtenerProductos() {
		return productoDao.findAll();
	}

	public Producto crearProducto(Producto producto) {
		return productoDao.crear(producto);
	}

	public Producto actualizarProducto(Producto producto) {
		return productoDao.actualizar(producto);
	}

	public Boolean verificarExistenciaProducto(Integer idProducto, Integer cantidad) {
		Producto producto = productoDao.obtenerPorId(idProducto);
		return (null != producto && null != producto.getExistencia()
				&& (producto.getExistencia().compareTo(cantidad) > 0));
	}

}
