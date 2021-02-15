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
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario extends AuditoriaBaseUtilModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usua_id")
	private Integer id;

	@Column(name = "usua_login")
	private String usuarioLogin;

	@Column(name = "usua_contrasenia")
	private String contrasenia;

	@Column(name = "usua_estado")
	private Boolean estado;

	@Column(name = "usua_nombre")
	private String nombre;

	@Column(name = "usua_tipo")
	private String tipo;

	@Column(name = "usua_email")
	private String correoElectronico;

	@Column(name = "usua_identificacion")
	private String identificacion;

	@OneToMany(mappedBy = "usuario")
	private List<Movimiento> movimientos;

	public Usuario() {
		// No Aplica
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

}