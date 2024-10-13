package com.dbp.projectofinal.usuario.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUsuarioDTO {
    @NotNull
    private String nombre;
    @NotNull
    @Email
    private String correo;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
}
