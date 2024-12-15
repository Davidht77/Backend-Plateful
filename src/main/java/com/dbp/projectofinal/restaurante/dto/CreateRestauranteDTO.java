package com.dbp.projectofinal.restaurante.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateRestauranteDTO {
    private String nombre_restaurante;
    private String horario;
    private String tipoRestaurante;
    private String email;
    private String direccion;
}
