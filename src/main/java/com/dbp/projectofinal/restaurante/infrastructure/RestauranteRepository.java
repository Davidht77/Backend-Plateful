package com.dbp.projectofinal.restaurante.infrastructure;

import com.dbp.projectofinal.restaurante.domain.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Page<Restaurante> findRestaurantesByTipoRestauranteContainingIgnoreCase(String tipo, Pageable pageable);
}
