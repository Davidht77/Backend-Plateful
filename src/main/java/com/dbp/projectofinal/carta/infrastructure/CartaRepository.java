package com.dbp.projectofinal.carta.infrastructure;

import com.dbp.projectofinal.carta.domain.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
}
