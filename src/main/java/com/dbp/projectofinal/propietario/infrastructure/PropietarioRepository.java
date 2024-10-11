package com.dbp.projectofinal.propietario.infrastructure;

import com.dbp.projectofinal.propietario.domain.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Long> {
}
