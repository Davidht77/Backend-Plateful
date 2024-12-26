package com.dbp.projectofinal.restaurante.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class EditRestaurantDTO {
    private String nombre_restaurante;
    private String nombre_carta;
    private String horario;
    private String tipoRestaurante;
    private String direccion;
}
