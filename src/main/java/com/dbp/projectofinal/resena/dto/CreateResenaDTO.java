package com.dbp.projectofinal.resena.dto;

import lombok.Data;

@Data
public class CreateResenaDTO {
    private int calificacion;
    private Long id_usuario;
    private Long id_restaurante;
}
