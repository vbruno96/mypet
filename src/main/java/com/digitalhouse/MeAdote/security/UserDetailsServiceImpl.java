package com.digitalhouse.MeAdote.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.model.Login;
import com.digitalhouse.MeAdote.repository.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private LoginRepository loginRepository;


	@Autowired
	public UserDetailsServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Login login = this.loginRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		return  new UserDetailsImpl(login);
	}

}