package com.dbp.projectofinal.auth.dto;

import com.dbp.projectofinal.usuario.domain.Category;
import lombok.Data;


@Data
public class RegisterReq {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String Phone;
    private Boolean isDriver=false;
    private Category Category;
}
