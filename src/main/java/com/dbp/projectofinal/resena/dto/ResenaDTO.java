package com.dbp.projectofinal.resena.dto;
import lombok.Data;

@Data
public class ResenaDTO {
    private Long id_resena;
    private String contenido;
    private int calificacion;
    private Long id_usuario;
    private String nombre_usuario;
    private Long id_restaurante;
    private String fecha;
}
