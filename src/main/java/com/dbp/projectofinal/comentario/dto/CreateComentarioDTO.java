package com.dbp.projectofinal.comentario.dto;

import lombok.Data;

@Data
public class CreateComentarioDTO {
    private String contenido;
    private Long id_resena;
    private Long id_usuario;
}
