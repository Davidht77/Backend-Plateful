package com.dbp.projectofinal.auth.dto;

import com.dbp.projectofinal.usuario.domain.Category;
import lombok.Data;


@Data
public class RegisterReq {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Category category;
}
