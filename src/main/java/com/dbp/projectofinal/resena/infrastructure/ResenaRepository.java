package com.dbp.projectofinal.resena.infrastructure;

import com.dbp.projectofinal.resena.domain.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByUsuarioId(Long idUsuario);
    List<Resena> findByRestauranteId(Long idRestaurante);
}
