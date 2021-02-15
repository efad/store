package com.hulkstore.rest.response;

import java.math.BigDecimal;

/**
 * @author Edison Agurto
 * 
 */

public class ProductoResponse {

	private Integer id;

	private String descripcion;

	private Boolean estado;

	private Integer existencia;

	private String nombre;

	private BigDecimal valor;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getExistencia() {
		return this.existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return this.valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}