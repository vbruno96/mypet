package com.digitalhouse.MeAdote.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.digitalhouse.MeAdote.model.Login;
import com.digitalhouse.MeAdote.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -10866901171389626L;
	
	
	private String username;
	private String password;
	
	private Set<? extends GrantedAuthority> roles = new HashSet<>();
	
	public UserDetailsImpl(Login login) {
		this.username = login.getEmail();
		this.password = login.getSenha();		
		this.roles = login.getRoles().stream()
									.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
									.collect(Collectors.toSet());
	}
	
	public UserDetailsImpl(Usuario usuario) {
		this(usuario.getLogin());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
