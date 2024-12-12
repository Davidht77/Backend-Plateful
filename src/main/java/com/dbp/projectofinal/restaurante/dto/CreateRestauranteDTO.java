package com.dbp.projectofinal.restaurante.dto;

import lombok.Data;

@Data
public class CreateRestauranteDTO {
    private String nombre_restaurante;
    private String horario;
    private String tipoRestaurante;
    private Long propietarioId;
    private Long cartaId;
    private Double calificacion_promedio;
    private Long ubicacionId;
    private Double latitude;
    private Double longitude;
}
