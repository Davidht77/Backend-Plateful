package com.dbp.projectofinal.auth.dto;

import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.usuario.domain.Category;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;


@Data
public class RegisterReq {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private String phone;
    private LocalDate date;
    private Category category;
}
