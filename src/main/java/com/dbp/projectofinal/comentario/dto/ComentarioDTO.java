package com.dbp.projectofinal.comentario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ComentarioDTO {
    private Long id_comentario;
    private Long id_resena;
    private Long id_usuario;
    private String nombre_usuario;
    private String contenido;
    private LocalDate fecha;
}
