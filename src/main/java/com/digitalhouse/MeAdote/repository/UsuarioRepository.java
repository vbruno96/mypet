package com.digitalhouse.MeAdote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalhouse.MeAdote.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
