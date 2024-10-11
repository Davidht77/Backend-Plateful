package com.dbp.projectofinal.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUsuarioDTO {
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
}
