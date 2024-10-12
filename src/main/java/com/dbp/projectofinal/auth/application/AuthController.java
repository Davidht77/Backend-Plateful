package com.dbp.projectofinal.auth.application;

import com.dbp.projectofinal.auth.domain.AuthService;
import org.e2e.e2e.auth.dto.JwtAuthResponse;
import org.e2e.e2e.auth.dto.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginReq loginReq) {
//        return ResponseEntity.ok(authService.login(loginReq));
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterReq req){
//        return ResponseEntity.ok(authService.register(req));
//    }
}