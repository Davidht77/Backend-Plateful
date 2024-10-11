package com.dbp.projectofinal.comentario.infrastructure;

import com.dbp.projectofinal.comentario.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
