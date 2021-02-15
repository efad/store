package com.hulkstore.test;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hulkstore.modelo.CatalogoDetalle.TipoUsuarioEnum;
import com.hulkstore.modelo.Usuario;
import com.hulkstore.modelo.dao.UsuarioDao;
import com.hulkstore.modelo.utils.AuditoriaBaseUtilModelo;
import com.hulkstore.modelo.utils.DaoGenericoUtil;

/**
 * @author Edison Agurto
 * 
 */

@RunWith(Arquillian.class)
public class UsuarioTest {

	private static final String NOMBRE = "Carlos Quintana";
	private static final String NOMBRE_MODIFICADO = "Carlos José Quintana";
	private static final String LOGIN = "carlos.quintana";
	private static final String CONTRASENIA = "9bf5bb3e4bd40e48b2a893d8928a13c0289f9a74404437ffc44070135c95f290";
	private static final String CLIENTE = "127.0.0.1";
	private static final String USUARIO_AUDITORIA = "edison.agurto";

	@Deployment
	public static Archive<?> crearDespliegueTemporal() {
		return ShrinkWrap.create(WebArchive.class, "usuario-test.war").addPackage(Usuario.class.getPackage())
				.addPackage(UsuarioDao.class.getPackage()).addPackage(DaoGenericoUtil.class.getPackage())
				.addPackage(AuditoriaBaseUtilModelo.class.getPackage()).addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	UsuarioDao usuarioDao;

	@Test
	public void pruebaPersistenciaUsuario() {

		Usuario usuario = new Usuario();
		asignarDataPrueba(usuario);

		usuarioDao.crear(usuario);
		Usuario usuarioObtenido = usuarioDao.obtenerPorId(usuario.getId());
		Assert.assertNotNull(usuarioObtenido.getId());
		Assert.assertEquals(CONTRASENIA, usuarioObtenido.getContrasenia());
		Assert.assertEquals(TipoUsuarioEnum.PUBLICO.name(), usuarioObtenido.getTipo());
		Assert.assertEquals(Boolean.TRUE, usuarioObtenido.getEstado());
		Assert.assertEquals(NOMBRE, usuarioObtenido.getNombre());
		Assert.assertEquals(LOGIN, usuarioObtenido.getUsuarioLogin());
		Assert.assertEquals(CLIENTE, usuarioObtenido.getCliente());
		Assert.assertNotNull(usuarioObtenido.getFechaModificacion());
		Assert.assertEquals(USUARIO_AUDITORIA, usuarioObtenido.getUsuarioModificacion());

		usuarioObtenido.setNombre(NOMBRE_MODIFICADO);

		usuarioDao.actualizar(usuarioObtenido);
		Usuario usuarioModificado = usuarioDao.obtenerPorId(usuarioObtenido.getId());

		Assert.assertEquals(NOMBRE_MODIFICADO, usuarioModificado.getNombre());

		usuarioDao.eliminar(usuarioObtenido);
		Usuario usuarioEliminado = usuarioDao.obtenerPorId(usuarioModificado.getId());
		Assert.assertNull(usuarioEliminado);
	}

	private void asignarDataPrueba(Usuario usuario) {

		usuario.setCliente(CLIENTE);
		usuario.setTipo(TipoUsuarioEnum.PUBLICO.name());
		usuario.setEstado(Boolean.TRUE);
		usuario.setContrasenia(CONTRASENIA);
		usuario.setFechaModificacion(new Date());
		usuario.setNombre(NOMBRE);
		usuario.setUsuarioLogin(LOGIN);
		usuario.setUsuarioModificacion(USUARIO_AUDITORIA);

	}

}
