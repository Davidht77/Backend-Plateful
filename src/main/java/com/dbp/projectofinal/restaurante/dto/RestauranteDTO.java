package com.dbp.projectofinal.restaurante.dto;

import lombok.Data;

@Data
public class RestauranteDTO {
    private Long id_restaurante;
    private String nombre_restaurante;
    private String horario;
    private String tipoRestaurante;
    private Long propietarioId;
    private String nombre_propietario;
    private Long cartaId;
    private String nombre_carta;
    private Double calificacion_promedio;
    private String direccion;
    private Double latitude;
    private Double longitude;
}

