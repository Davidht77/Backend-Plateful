package org.e2e.e2e.auth.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String email;
    private String password;
}
