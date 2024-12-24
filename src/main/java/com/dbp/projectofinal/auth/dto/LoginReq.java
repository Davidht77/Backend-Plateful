package org.e2e.e2e.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class LoginReq {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
