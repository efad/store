package com.hulkstore.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;

/**
 * @author Edison Agurto
 * 
 */
@Entity
@NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c")
public class Catalogo extends AuditoriaBaseUtilModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Integer idCatalogo;

	@Column(name = "cat_estado")
	private Boolean estado;

	@Column(name = "cat_alias")
	private String alias;

	@OneToMany(mappedBy = "catalogo")
	private List<CatalogoDetalle> catalogoDetalles;

	public Catalogo() {
		// No aplica
	}

	public Integer getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<CatalogoDetalle> getCatalogoDetalles() {
		return this.catalogoDetalles;
	}

	public void setCatalogoDetalles(List<CatalogoDetalle> catalogoDetalles) {
		this.catalogoDetalles = catalogoDetalles;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}