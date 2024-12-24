package com.dbp.projectofinal.resena.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreateResenaDTO {
    @NotNull
    private int calificacion;
    @NotNull
    private String contenido;
    @NotNull
    private Long id_usuario;
    @NotNull
    private Long id_restaurante;
}
