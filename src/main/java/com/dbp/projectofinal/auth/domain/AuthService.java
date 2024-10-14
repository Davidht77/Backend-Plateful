package com.dbp.projectofinal.auth.domain;

import com.dbp.projectofinal.auth.dto.RegisterReq;
import com.dbp.projectofinal.config.JwtService;
import com.dbp.projectofinal.eventos.SendMailEvent;
import com.dbp.projectofinal.usuario.domain.Category;
import com.dbp.projectofinal.usuario.domain.Role;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.infrastructure.BaseUserRepository;
import com.dbp.projectofinal.usuario.infrastructure.RoleRepository;
import org.e2e.e2e.auth.dto.JwtAuthResponse;
import org.e2e.e2e.auth.dto.LoginReq;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

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
        usuario.setNombre(registerReq.getFirstName());
        usuario.setEmail(registerReq.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        usuario.setTelefono(registerReq.getPhone());

        Role propietarioRole = roleRepository.findByName("ROLE_PROPIETARIO")
                .orElseThrow(() -> new RuntimeException("Error: Role PROPIETARIO is not found."));
        Role clienteRole = roleRepository.findByName("ROLE_CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Role CLIENTE is not found."));

        if (registerReq.getCategory() == Category.PROPIETARIO) {
            usuario.setRoles(Set.of(propietarioRole));
        } else {
            usuario.setRoles(Set.of(clienteRole));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("to",usuario.getEmail());
        map.put("name",usuario.getNombre());
        applicationEventPublisher.publishEvent(new SendMailEvent(map) );
        userRepository.save(usuario);

        Usuario user = new Usuario();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
        String token = jwtService.generateToken(userDetails);

        return new JwtAuthResponse(token);
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


//    public JwtAuthResponse login(LoginReq req) {
//        Optional<Usuario> user;
//        user = userRepository.findByEmail(req.getEmail());
//
//        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");
//
//        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
//            throw new IllegalArgumentException("Password is incorrect");
//
//        JwtAuthResponse response = new JwtAuthResponse();
//
//        response.setToken(jwtService.generateToken(user.get()));
//        return response;
//    }
//
//    public JwtAuthResponse register(RegisterReq req){
//        Optional<User> user = userRepository.findByEmail(req.getEmail());
//        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");
//
//        if (req.getIsDriver()) {
//            Driver driver = new Driver();
//            driver.setCategory(req.getCategory());
//            driver.setVehicle(modelMapper.map(req.getVehicle(), Vehicle.class));
//            driver.setTrips(0);
//            driver.setAvgRating(0f);
//            driver.setCreatedAt(ZonedDateTime.now());
//            driver.setRole(Role.DRIVER);
//            driver.setFirstName(req.getFirstName());
//            driver.setLastName(req.getLastName());
//            driver.setEmail(req.getEmail());
//            driver.setPassword(passwordEncoder.encode(req.getPassword()));
//            driver.setPhoneNumber(req.getPhone());
//
//            userRepository.save(driver);
//
//            JwtAuthResponse response = new JwtAuthResponse();
//            response.setToken(jwtService.generateToken(driver));
//            return response;
//        }
//        else {
//            Passenger passenger = new Passenger();
//            passenger.setCreatedAt(ZonedDateTime.now());
//            passenger.setRole(Role.PASSENGER);
//            passenger.setFirstName(req.getFirstName());
//            passenger.setLastName(req.getLastName());
//            passenger.setEmail(req.getEmail());
//            passenger.setPassword(passwordEncoder.encode(req.getPassword()));
//            passenger.setPhoneNumber(req.getPhone());
//            passenger.setAvgRating(0f);
//            passenger.setTrips(0);
//
//            userRepository.save(passenger);
//
//            JwtAuthResponse response = new JwtAuthResponse();
//            response.setToken(jwtService.generateToken(passenger));
//            return response;
//        }
//
//    }
}