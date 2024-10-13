package com.dbp.projectofinal.comentario.infrastructure;

import com.dbp.projectofinal.comentario.domain.Comentario;
import com.dbp.projectofinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuario(Usuario usuario);
}
