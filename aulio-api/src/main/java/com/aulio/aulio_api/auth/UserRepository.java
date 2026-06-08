/**
 * 
 */
package com.aulio.aulio_api.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que extiende de JpaRepository para acceder a los métodos básicos de JPA save(), findById(), findAll(),
 *  delete()....etc sin tener que crearlos manualmente.
 */
public interface UserRepository extends JpaRepository<User, Long> { // Parametro User, porque lo usaremos para esta clase y Long es por el tipo de variable Id marcado con @Id
	//METODOS ADICIONALES
	
	Optional<User> findByEmail(String email); // Método para buscar una instancia de Usuario por su Email
	
}
