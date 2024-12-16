package com.dbp.projectofinal.usuario.infrastructure;

import com.dbp.projectofinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT u.*, r.name AS role_name " +
            "FROM usuarios u " +
            "JOIN user_roles ur ON u.id_usuario = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE u.id_usuario = :userId", nativeQuery = true)
    List<Object[]> findUsuarioWithRoles(@Param("userId") Long userId);
}
