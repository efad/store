package com.hulkstore.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;

/**
 * @author Edison Agurto
 * 
 */
@Entity
@NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
public class Movimiento extends AuditoriaBaseUtilModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movi_id")
	private Integer id;

	@Column(name = "movi_cantidad")
	private Integer cantidad;

	@Column(name = "movi_estado")
	private Boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "movi_fecha")
	private Date fecha;

	@Column(name = "movi_valor")
	private BigDecimal valor;

	@Column(name = "movi_tipo")
	private String tipoMovimiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_id")
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usua_id")
	private Usuario usuario;

	public Movimiento() {
		// No Aplica
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}