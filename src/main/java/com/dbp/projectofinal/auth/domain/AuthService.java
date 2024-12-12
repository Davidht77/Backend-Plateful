package com.dbp.projectofinal.auth.domain;

import com.dbp.projectofinal.auth.dto.RegisterReq;
import com.dbp.projectofinal.config.JwtService;
import com.dbp.projectofinal.eventos.SendMailEvent;
import com.dbp.projectofinal.usuario.domain.Category;
import com.dbp.projectofinal.usuario.domain.Role;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.infrastructure.BaseUserRepository;
import com.dbp.projectofinal.usuario.infrastructure.RoleRepository;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.e2e.e2e.auth.dto.JwtAuthResponse;
import org.e2e.e2e.auth.dto.LoginReq;
import org.e2e.e2e.auth.exceptions.UserAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private final BaseUserRepository<Usuario> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AuthService(BaseUserRepository<Usuario> userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = new ModelMapper();
    }

    public JwtAuthResponse register(RegisterReq registerReq) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registerReq.getName());
        Optional<Usuario> user1 = usuarioRepository.findByEmail(registerReq.getEmail());
        if(user1.isPresent()){
            throw new UserAlreadyExistException("");
        }
        usuario.setEmail(registerReq.getEmail());

        usuario.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        usuario.setTelefono(registerReq.getPhone());
        usuario.setFechaNacimiento(registerReq.getDate());


        Role propietarioRole = roleRepository.findByName("ROLE_PROPIETARIO")
                .orElseThrow(() -> new RuntimeException("Error: Role PROPIETARIO is not found."));
        Role clienteRole = roleRepository.findByName("ROLE_CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Role CLIENTE is not found."));

        if (registerReq.getCategory() == Category.PROPIETARIO) {
            usuario.setRoles(Set.of(propietarioRole));
        } else {
            usuario.setRoles(Set.of(clienteRole));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
        String token = jwtService.generateToken(userDetails);

        userRepository.save(usuario);

        Map<String,Object> map = new HashMap<>();
        map.put("to",usuario.getEmail());
        map.put("name",usuario.getNombre());
        applicationEventPublisher.publishEvent(new SendMailEvent(map));

        return new JwtAuthResponse(token, registerReq.getCategory().name());
    }

    public JwtAuthResponse login(LoginReq loginReq) {
        // Autenticación del usuario
        Usuario usuario = userRepository.findByEmail(loginReq.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar la contraseña
        if (!passwordEncoder.matches(loginReq.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar el token
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
        String token = jwtService.generateToken(userDetails);

        // Obtener el rol del usuario
        String role = usuario.getRoles().stream().findFirst()
                .map(Role::getName)
                .orElse("Unknown");

        return new JwtAuthResponse(token);
    }

}