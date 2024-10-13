package com.dbp.projectofinal.resena.infrastructure;

import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByUsuario(Usuario usuario);
    List<Resena> findByRestaurante(Restaurante restaurante);
}
