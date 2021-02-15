package com.hulkstore.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;

/**
 * @author Edison Agurto
 * 
 */
@Entity
@Table(name = "producto")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto extends AuditoriaBaseUtilModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
	private Integer id;

	@Column(name = "prod_descripcion")
	private String descripcion;

	@Column(name = "prod_estado")
	private Boolean estado;

	@Column(name = "prod_existencia")
	private Integer existencia;

	@Column(name = "prod_nombre")
	private String nombre;

	@Column(name = "prod_valor")
	private BigDecimal valor;

	@OneToMany(mappedBy = "producto")
	private List<Movimiento> movimientos;

	public Producto() {
		// No Aplica
	}

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

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setProducto(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setProducto(null);

		return movimiento;
	}

}