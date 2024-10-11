package com.dbp.projectofinal.resena.dto;
import lombok.Data;

@Data
public class ResenaDTO {
    private Long id_resena;
    private int calificacion;
    private Long id_usuario;
    private Long id_restaurante;
    private String fecha;
}
