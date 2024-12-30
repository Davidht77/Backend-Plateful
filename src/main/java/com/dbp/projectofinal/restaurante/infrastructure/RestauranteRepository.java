package com.dbp.projectofinal.restaurante.infrastructure;

import com.dbp.projectofinal.restaurante.domain.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Page<Restaurante> findRestaurantesByTipoRestauranteContainingIgnoreCase(String tipo, Pageable pageable);

    Page<Restaurante> findRestaurantesBynameContainingIgnoreCase(String tipo, Pageable pageable);

    @Query("SELECT r FROM Restaurante r WHERE r.propietario.id_usuario = :propietarioId")
    List<Restaurante> findByPropietarioId(@Param("propietarioId") Long propietarioId);
}
