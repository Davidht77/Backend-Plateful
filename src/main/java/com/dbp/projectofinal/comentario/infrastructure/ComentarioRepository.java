package com.dbp.projectofinal.comentario.infrastructure;

import com.dbp.projectofinal.comentario.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByResenaId(Long resenaId);
    List<Comentario> findByUsuarioId(Long usuarioId);
}
