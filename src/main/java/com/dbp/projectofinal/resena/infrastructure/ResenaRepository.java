package com.dbp.projectofinal.resena.infrastructure;

import com.dbp.projectofinal.resena.domain.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
}
