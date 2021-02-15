package com.hulkstore.servicios;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hulkstore.modelo.Usuario;
import com.hulkstore.modelo.dao.UsuarioDao;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
public class SeguridadServicio {

	@Inject
	private UsuarioDao usuarioDao;

	public List<Usuario> obtenerTodos() {

		return usuarioDao.findAll();
	}

	public Usuario crear(Usuario usuario) {
		return usuarioDao.crear(usuario);

	}

	public Usuario actualizar(Usuario usuario) {
		return usuarioDao.actualizar(usuario);

	}

	public Usuario verificarCredenciales(String usuario, String password) {
		if (null != usuario && null != password) {
			return usuarioDao.obtenerUsuario(usuario, getSHA256(password));
		}
		return null;
	}

	public static String getSHA256(String input) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes(StandardCharsets.UTF_8));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}

}
