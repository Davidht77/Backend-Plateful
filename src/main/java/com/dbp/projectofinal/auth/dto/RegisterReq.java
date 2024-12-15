package com.dbp.projectofinal.auth.dto;

import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.usuario.domain.Category;
import lombok.Data;

import java.time.LocalDate;


@Data
public class RegisterReq {
    private String name;
    private String email;
    private String password;
    private String phone;
    private LocalDate date;
    private Category category;
}
