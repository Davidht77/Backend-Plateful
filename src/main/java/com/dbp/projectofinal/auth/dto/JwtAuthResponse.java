package org.e2e.e2e.auth.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String role;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
