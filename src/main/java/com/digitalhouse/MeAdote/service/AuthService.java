package com.digitalhouse.MeAdote.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.exception.InvalidCredentialsException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.AuthResponse;
import com.digitalhouse.MeAdote.model.Credential;
import com.digitalhouse.MeAdote.model.Usuario;
import com.digitalhouse.MeAdote.security.JwtUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final UsuarioService usuarioService;
	private final JwtUtil jwtUtil;
	
	public AuthResponse login(Credential credential) throws InvalidCredentialsException {
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken
				(credential.getUsername(), credential.getPassword());
		
		Authentication authenticate;
		
		try {
			authenticate = this.authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
			throw new InvalidCredentialsException();
		}		
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		Usuario usuario;
		try {
			usuario = this.usuarioService.findByUsername(credential.getUsername());
		}
		catch (ObjectNotFoundException e) {
			throw new InvalidCredentialsException();
		}		
		
		String jwt = this.jwtUtil.generateToken(usuario);
		
		return AuthResponse.builder()
				.jwt(jwt)
				.build();
	}
	
	
}
