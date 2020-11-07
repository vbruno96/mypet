package com.digitalhouse.MeAdote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalhouse.MeAdote.model.Login;


public interface LoginRepository extends JpaRepository<Login,Long>{
	
	Optional<Login> findByEmail(String email);

}
