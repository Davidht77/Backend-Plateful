package com.dbp.projectofinal.carta.dto;

import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.RestauranteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartaDTO {

    private String nombre;
    private LocalDate fecha_actualizacion = LocalDate.now();
    @NotNull
    private Long restauranteId;

}
