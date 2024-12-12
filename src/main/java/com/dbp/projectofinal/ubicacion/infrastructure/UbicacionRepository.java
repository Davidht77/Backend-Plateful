package com.dbp.projectofinal.ubicacion.infrastructure;

import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
}
