package com.dbp.projectofinal.plato.infrastructure;

import com.dbp.projectofinal.plato.domain.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findByCartaId(Long cartaId);
    List<Plato> findByDisponibilidadTrue();
}
