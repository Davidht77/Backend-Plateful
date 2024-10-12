package com.dbp.projectofinal.propietario.infrastructure;

import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.usuario.infrastructure.BaseUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Long> {
    Optional<Propietario> findByEmail(String email);
}
