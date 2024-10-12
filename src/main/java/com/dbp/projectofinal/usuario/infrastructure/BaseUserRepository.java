package com.dbp.projectofinal.usuario.infrastructure;

import com.dbp.projectofinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository<T extends Usuario> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
}
