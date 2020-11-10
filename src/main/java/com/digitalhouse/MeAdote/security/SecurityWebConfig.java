package com.digitalhouse.MeAdote.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsServiceImpl usuarioService;
	
	@Autowired
	public SecurityWebConfig (UserDetailsServiceImpl usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/**", "/swagger-ui.html", "/webjars/**").hasRole("admin")
		.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
		
		.antMatchers(HttpMethod.POST, "/especies").hasRole("admin")
		.antMatchers(HttpMethod.PUT, "/especies/**").hasRole("admin")
		.antMatchers(HttpMethod.DELETE, "/especies/**").hasRole("admin")
		
		.antMatchers(HttpMethod.GET, "/usuarios/**/roles").hasRole("admin")
		.antMatchers(HttpMethod.POST, "/usuarios/**/roles").hasRole("admin")
		.antMatchers(HttpMethod.DELETE, "/usuarios/**/roles").hasRole("admin")
		
		.antMatchers(HttpMethod.GET, "/roles").hasRole("admin")
		.antMatchers(HttpMethod.POST, "/roles").hasRole("admin")
		.antMatchers(HttpMethod.PUT, "/roles").hasRole("admin")
		.antMatchers(HttpMethod.DELETE, "/roles").hasRole("admin")
		
		.anyRequest().authenticated()

		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().httpBasic()
		.and().cors().disable().csrf().disable();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder());

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
