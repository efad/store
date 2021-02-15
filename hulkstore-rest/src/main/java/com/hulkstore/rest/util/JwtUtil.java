package com.hulkstore.rest.util;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtil {

	private static final Logger LOG = Logger.getLogger(JwtUtil.class.getName());

	private static final String CLAVE_TOKEN = "Ute.2o2o#";

	public static String generarJWT(String usuario, long tiempoToken) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long milisegundosActuales = System.currentTimeMillis();
		Date fechaActual = new Date(milisegundosActuales);

		byte[] claveSecretaBytes = DatatypeConverter.parseBase64Binary(CLAVE_TOKEN);
		Key signingKey = new SecretKeySpec(claveSecretaBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId("jwt").setIssuedAt(fechaActual).setSubject(usuario)
				.signWith(signatureAlgorithm, signingKey);

		if (tiempoToken > 0) {
			long expMillis = milisegundosActuales + tiempoToken;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public static Claims decodeJWT(String jwt) {
		try {
			return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(CLAVE_TOKEN)).parseClaimsJws(jwt)
					.getBody();
		} catch (SignatureException se) {
			LOG.log(Level.SEVERE, "El token enviado no es valido");
			return null;
		} catch (ExpiredJwtException ee) {
			LOG.log(Level.SEVERE, "El token enviado esta expirado");
			return null;
		}
	}

	public static boolean validarToken(HttpHeaders headers) {
		List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
		if (authHeaders != null && !authHeaders.isEmpty()) {
			String token = authHeaders.get(0).replace("Bearer ", "");
			Claims claims = JwtUtil.decodeJWT(token);
			return (null != claims);
		} else {
			LOG.log(Level.SEVERE, "No se envia el token en la peticion.");
			return false;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String token = generarJWT("prueba", (1000));
		Thread.sleep(2000);
		Claims claims = decodeJWT(token);
		System.out.println(claims.getExpiration());
	}
}
