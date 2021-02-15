package com.hulkstore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;

/**
 * @author Edison Agurto
 * 
 */
@Entity
@Table(name = "catalogo_detalle")
@NamedQuery(name = "CatalogoDetalle.findAll", query = "SELECT c FROM CatalogoDetalle c")
public class CatalogoDetalle extends AuditoriaBaseUtilModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_det_id")
	private Integer id;

	@Column(name = "cat_det_descripcion")
	private String descripcion;

	@Column(name = "cat_det_estado")
	private Boolean estado;

	@Column(name = "cat_det_nombre")
	private String nombre;

	@Column(name = "cat_det_alias")
	private String aliasDetalle;

	@Column(name = "cat_det_orden")
	private Integer orden;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id")
	private Catalogo catalogo;

	public CatalogoDetalle() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public String getAliasDetalle() {
		return aliasDetalle;
	}

	public void setAliasDetalle(String aliasDetalle) {
		this.aliasDetalle = aliasDetalle;
	}

	public enum TipoMovimientoEnum {
		ENTRADA("Ingreso Producto"), SALIDA("Salida Producto"), REPARACION("Reparación Producto");

		private String value;

		private TipoMovimientoEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	public enum TipoUsuarioEnum {
		ADMINISTRADOR("Administrador"), PUBLICO("Público");

		private String value;

		private TipoUsuarioEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}
}