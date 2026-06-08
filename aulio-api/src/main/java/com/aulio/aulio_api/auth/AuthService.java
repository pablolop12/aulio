/**
 * 
 */
package com.aulio.aulio_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	//Método de Login, donde se recibe el DTO de la request y se almacena en una variable request.
	public LoginResponseDTO login (LoginRequestDTO request) {
		
		//Busca el usuario por el email y almacena el usuario en la variable user para poder verificar su campo contraseña a continuacion
		User user = userRepository.findByEmail(request.getEmail())
			    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		//Verificar si la contraseña coincide con la de la base de datos
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) { // La compara con la contraseña codificada
			throw new RuntimeException("Contraseña no coincidente");
		}
		
		
		//Preparamos la respuesta, creando una instancia del DTO de respuesta y rellenandolo con los parametros que necesita
		String token = jwtService.generateToken(user);
		
		LoginResponseDTO response = new LoginResponseDTO();
		response.setToken(token);
		response.setRole(user.getRole());
		
		return response;
	}
	


}
