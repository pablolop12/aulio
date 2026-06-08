/**
 * 
 */
package com.aulio.aulio_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aulio.aulio_api.auth.dto.LoginRequestDTO;
import com.aulio.aulio_api.auth.dto.LoginResponseDTO;

/**
 * Clase donde crearemos la lógica de negocio principal. Crearemos aquí todos los métodos que usaremos en el controlador
 * de AuthController.
 */

@Service
public class AuthService {
	
	//Inyección de dependecias de UserRepository para usar los métodos extendidos en la interfaz
	@Autowired
	private UserRepository userRepository;
	


}
