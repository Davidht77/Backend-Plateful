package com.dbp.projectofinal.plato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlatoDTO {
    @NotNull
    private String nombre;
    @NotNull
    private Double precio;
    private Boolean disponibilidad = true;
    @NotNull
    private Long id_carta;
}
