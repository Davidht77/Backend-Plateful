package com.dbp.projectofinal.propietario.domain;

import com.dbp.projectofinal.restaurante.domain.RestauranteDTO;
import com.dbp.projectofinal.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Propietario extends Usuario {
    private String tipoDocumento;
    private String numeroDocumento;
    private String fotoPerfil;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RestauranteDTO> restaurantes;

    public Propietario(Long id_usuario) {
        super(id_usuario);
    }
}
