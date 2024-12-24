package com.dbp.projectofinal.comentario.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreateComentarioDTO {
    @NotNull
    private String contenido;
    @NotNull
    private Long id_resena;
    @NotNull
    private Long id_usuario;
}
