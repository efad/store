package com.hulkstore.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hulkstore.modelo.Producto;
import com.hulkstore.rest.response.ProductoResponse;
import com.hulkstore.servicios.InventarioServicio;

/**
 * 
 * @Author Edison Agurto
 */

@Path("producto")
public class InventarioRestService {

	@Inject
	InventarioServicio inventarioServicio;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("todos")
	public Response obtenerProductos() {
		try {
			List<ProductoResponse> listaProductos = asignacionProducto(inventarioServicio.obtenerProductos());
			return Response.status(Response.Status.OK).entity(listaProductos).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	private List<ProductoResponse> asignacionProducto(List<Producto> obtenerProductos) {
		List<ProductoResponse> productosResponse = new ArrayList<>();
		if (obtenerProductos.isEmpty()) {
			return Collections.emptyList();
		} else {
			for (Producto item : obtenerProductos) {
				ProductoResponse productoResponse = new ProductoResponse();
				productoResponse.setNombre(item.getNombre());
				productoResponse.setDescripcion(item.getDescripcion());
				productoResponse.setEstado(item.getEstado());
				productoResponse.setExistencia(item.getExistencia());
				productoResponse.setId(item.getId());
				productoResponse.setValor(item.getValor());
				productosResponse.add(productoResponse);
			}
			return productosResponse;
		}

	}

	@PUT
	@Path("actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarProducto(Producto producto, @QueryParam("id") String id) {
		try {
			return Response.ok(inventarioServicio.actualizarProducto(producto)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@POST
	@Path("crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto producto) {
		try {
			inventarioServicio.crearProducto(producto);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

}
