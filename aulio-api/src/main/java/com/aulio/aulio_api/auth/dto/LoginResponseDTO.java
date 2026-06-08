/**
 * 
 */
package com.aulio.aulio_api.auth.dto;

import lombok.Data;
import com.aulio.aulio_api.auth.Role;

/**
 * DTO con la respuesta que va a devolver el servidor al usuario despues de logearse (token JWT y role en este caso)
 */
@Data
public class LoginResponseDTO {

	private String token;
	private Role role; 
}
