package com.dbp.projectofinal.auth.application;

import com.dbp.projectofinal.auth.domain.AuthService;
import com.dbp.projectofinal.auth.dto.RegisterReq;
import org.e2e.e2e.auth.dto.JwtAuthResponse;
import org.e2e.e2e.auth.dto.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginReq loginReq) {
        return ResponseEntity.ok(authService.login(loginReq));
    }

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hola, Deployaste el proyecto");
    }
    

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        // Verificar que la contraseña no sea nula
        if (req.getPassword() == null || req.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña no puede ser nula o vacía");
        }

        JwtAuthResponse response = authService.register(req);
        return ResponseEntity.ok(response);
    }

}
