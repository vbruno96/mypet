package com.digitalhouse.MeAdote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalhouse.MeAdote.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> getByName(String name);

}
