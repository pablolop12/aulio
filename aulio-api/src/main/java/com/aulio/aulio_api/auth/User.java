/**
 * 
 */
package com.aulio.aulio_api.auth;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad usuario para la autenticación
 */

@Entity // Etiqueta para marcar que esto es una entidad
@Data // Lombok, getters/setters..etc
@NoArgsConstructor // Lombok crea constructor sin argumentos
@AllArgsConstructor // Lombok crea constructor con todos los argumentos
@Table(name = "users") // Nombramos la tabla a users porque 'user' es una palabra reservada de Postgre y daría error
public class User {
	
	@Id // Asignamos este campo como el Id para esta tentidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Etiqueta para autogenerar Id ascendentes y no repetidos
	private Long id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING) // Anotación para guardar los roles como String y no como número
	private Role role;
	
	@NotNull
	private boolean mustChangePassword; // Booleano que identifica si es tu primera vez logeando y te obliga a cambiar la contraseña
	
	private String resetToken; // Campo donde se almacenara el token de reset de contraseña
	
	private LocalDateTime resetTokenExpiry; // Campo donde se almacenará la caducidad del token de reset de contraseñá

}
