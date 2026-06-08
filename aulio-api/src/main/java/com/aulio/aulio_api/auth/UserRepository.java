/**
 * 
 */
package com.aulio.aulio_api.auth;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que extiende de JpaRepository para acceder a los métodos básicos de JPA save(), findById(), findAll(),
 *  delete()....etc sin tener que crearlos manualmente.
 */
public interface UserRepository extends JpaRepository {

}
