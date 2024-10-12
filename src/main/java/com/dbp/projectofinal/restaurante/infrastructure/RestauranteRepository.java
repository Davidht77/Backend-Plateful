package com.dbp.projectofinal.restaurante.infrastructure;

import com.dbp.projectofinal.restaurante.domain.RestauranteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteDTO, Long> {

}
