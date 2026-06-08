package com.aulio.aulio_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aulio.aulio_api.auth.dto.LoginRequestDTO;
import com.aulio.aulio_api.auth.dto.LoginResponseDTO;

@RestController
@RequestMapping ("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	@PostMapping("login")
	public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO dto){
	LoginResponseDTO response = this.authService.login(dto);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

