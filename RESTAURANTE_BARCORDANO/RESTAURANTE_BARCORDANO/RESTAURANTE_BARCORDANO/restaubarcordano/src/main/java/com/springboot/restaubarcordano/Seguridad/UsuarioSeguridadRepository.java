package com.springboot.restaubarcordano.Seguridad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSeguridadRepository extends JpaRepository<UsuarioSeguridad, Long> {
    Optional<UsuarioSeguridad> findByUsername(String username);
}
