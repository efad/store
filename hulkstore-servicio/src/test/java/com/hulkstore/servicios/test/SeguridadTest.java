package com.hulkstore.servicios.test;

import org.junit.Assert;
import org.junit.Test;

import com.hulkstore.servicios.SeguridadServicio;

public class SeguridadTest {
	private static final String CADENA_SIN_ENCRIPTAR = "123";
	private static final String CADENA_ENCRIPTADA = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";

	@Test
	public void pruebaEncripcion() {
		String cadenaAProbar = SeguridadServicio.getSHA256(CADENA_SIN_ENCRIPTAR);
		Assert.assertEquals(CADENA_ENCRIPTADA, cadenaAProbar);
	}

}
