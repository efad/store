package com.hulkstore.rest;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hulkstore.modelo.Usuario;
import com.hulkstore.rest.request.LoginRequest;
import com.hulkstore.rest.response.LoginResponse;
import com.hulkstore.rest.util.JwtUtil;
import com.hulkstore.servicios.SeguridadServicio;

/**
 * @author Edison Agurto
 * 
 */

@Stateless
@Path("seguridad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeguridadRestService implements Serializable {

	private static final Logger LOG = Logger.getLogger(SeguridadRestService.class.getName());
	private static final long serialVersionUID = -6695567235753333749L;

	private static final long TIEMPO_EXPIRACION_TOKEN = (1000 * 60 * 60 * 12);

	@Inject
	private SeguridadServicio seguridadServicio;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest parametrosLogin) {
		try {
			Usuario usuario = seguridadServicio.verificarCredenciales(parametrosLogin.getUsuario(),
					parametrosLogin.getPassword());
			if (null != usuario && null != usuario.getId()) {
				return configurarUsuarioValido(usuario, parametrosLogin);
			} else {
				return configurarUsuarioInvalido(parametrosLogin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	private Response configurarUsuarioInvalido(LoginRequest parametrosLogin) {
		LOG.log(Level.INFO, "LOGIN REST ===> Usuario o password no es correcto, Usuario: {0}, codigo aplicacion: {1}",
				new Object[] { parametrosLogin.getUsuario(), parametrosLogin.getCodigoAplicacion() });
		return Response.status(Response.Status.BAD_REQUEST).entity("El usuario o el password no es correcto.")
				.type(MediaType.APPLICATION_JSON).build();

	}

	private Response configurarUsuarioValido(Usuario usuario, LoginRequest parametrosLogin) {
		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setIdPersona(usuario.getId());
		loginResponse.setUsuario(usuario.getUsuarioLogin());
		loginResponse.setEmail(usuario.getCorreoElectronico());
		loginResponse.setIdentificacion(usuario.getIdentificacion());
		loginResponse.setNombre(usuario.getNombre());

		String token = JwtUtil.generarJWT(usuario.getUsuarioLogin(), TIEMPO_EXPIRACION_TOKEN);
		loginResponse.setToken("Bearer " + token);

		LOG.log(Level.INFO, "LOGIN REST ===> Usuario logueado, Usuario: {0}, codigo aplicacion: {1}",
				new Object[] { usuario.getUsuarioLogin(), parametrosLogin.getCodigoAplicacion() });

		return Response.status(Response.Status.OK).entity(loginResponse).type(MediaType.APPLICATION_JSON).build();
	}

}
