package com.dbp.projectofinal.restaurante.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreateRestauranteDTO {
    @NotNull
    private String nombre_restaurante;
    @NotNull
    private String horario;
    @NotNull
    private String tipoRestaurante;
    @NotNull
    private String direccion;
}
