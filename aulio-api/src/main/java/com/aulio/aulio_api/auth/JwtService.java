/**
 * 
 */
package com.aulio.aulio_api.auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Clase donde crearemos la lógica de la autenticación por tokens. Crearemos
 * aquí todos los métodos que usaremos en el controlador de JwtController.
 * 
 * Principalmente necesitaremos estos metodos - Generación de JWT del usuario -
 * Extraer email de un token - Validación de token no expirado
 */
public class JwtService {

	@Value("${jwt.secret}") // importamos el valor de la clave secreta de JWT del archivo properties
	private String secretKey;

	@Value("${jwt.expiration}") // importamos el valor de expiración del token JWT del archivo properties
	private long expiration;

	// Método auxiliar que convierte nuesta secretKey del properties de String a
	// objeto SecretKey para poder generar el token
	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// MÉTODO DE GENERACION DEL JWT DEL USUARIO. El método recibe un User y devuelve un String (el token)
	public String generateToken(User user) {
		return Jwts.builder()
				.subject(user.getEmail())
				.claim("role", user.getRole())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey()).compact();
	}
	
	//MÉTODO PARA EXTRAER EL EMAIL
	public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
	
	// MÉTODO PARA VALIDACIÓN DE TOKEN NO EXPIRADO
	public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	

}
