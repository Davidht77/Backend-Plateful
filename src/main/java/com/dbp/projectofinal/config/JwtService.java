package com.dbp.projectofinal.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dbp.projectofinal.usuario.domain.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UsuarioService userService;

    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public String generateToken(UserDetails data){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 10);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(data.getUsername())
                .withClaim("role", data.getAuthorities().toString())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public boolean validateToken(String token, String userEmail) throws AuthenticationException {

        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);

        return true;
    }
}
