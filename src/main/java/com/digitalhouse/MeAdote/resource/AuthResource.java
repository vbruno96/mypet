package com.digitalhouse.MeAdote.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhouse.MeAdote.exception.InvalidCredentialsException;
import com.digitalhouse.MeAdote.model.AuthResponse;
import com.digitalhouse.MeAdote.model.Credential;
import com.digitalhouse.MeAdote.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthResource {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody Credential credential) throws InvalidCredentialsException {
		AuthResponse login = this.authService.login(credential);
		
		return ResponseEntity.ok(login);
	}
}
