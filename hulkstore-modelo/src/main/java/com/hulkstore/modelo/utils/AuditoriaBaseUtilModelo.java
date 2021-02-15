/**
 * 
 */
package com.hulkstore.modelo.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Edison Agurto
 *
 */
@MappedSuperclass
public class AuditoriaBaseUtilModelo implements Serializable {

	private static final long serialVersionUID = -1955114067165038267L;

	@Column(name = "audi_usuario")
	private String usuarioModificacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audi_fecha", nullable = false)
	private Date fechaModificacion;

	@Column(name = "audi_cliente")
	private String cliente;

	public AuditoriaBaseUtilModelo() {
		// No Aplica
	}

	public String getUsuarioFechaModificacion() {

		SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		StringBuilder cad = new StringBuilder();
		if (!StringUtils.isEmpty(usuarioModificacion))
			cad.append(usuarioModificacion).append(" (").append(fechaFormateada.format(this.fechaModificacion))
					.append(")");

		return cad.toString();
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
