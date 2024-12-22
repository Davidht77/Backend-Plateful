package com.dbp.projectofinal.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
    private Long id_usuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String role;
}
