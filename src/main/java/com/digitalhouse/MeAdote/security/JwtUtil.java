package com.digitalhouse.MeAdote.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digitalhouse.MeAdote.model.Login;
import com.digitalhouse.MeAdote.model.Role;
import com.digitalhouse.MeAdote.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	
	@Value("${jwt.expiration}")
	private Long exp;
	
	public String generateToken(Usuario usuario) {
		return this.generateToken(usuario.getLogin());
	}
	
	public String generateToken(Login login) {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("usrid",  login.getId());
		claims.put("roles", login.getSimpleRoles());
		
		return Jwts.builder()
				.setSubject(login.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + exp))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.addClaims(claims)
				.compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
				return  now != null && now.before(expirationDate);
		}
		return false;
    }

	public Login getLogin(String token) {
		Claims claims = this.getClaims(token);

		Long usrid = claims.get("usrid", Long.class);		
		@SuppressWarnings("unchecked")
		List<String> roles = claims.get("roles", List.class);		
		String subject = claims.getSubject();

		return Login.builder()
				.id(usrid)
				.roles(roles.stream()
						.map(s -> Role.builder()
								.id(Long.valueOf(s.split("_")[0]))
								.name(s.split("_")[1]).build())
						.collect(Collectors.toSet()))
				.email(subject)
				.build();
	}


	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}

	}
}
