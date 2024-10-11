package com.dbp.projectofinal.plato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlatoDTO {
    private String nombre;
    private Double precio;
    private Boolean disponibilidad;
    private Long id_carta;
}
