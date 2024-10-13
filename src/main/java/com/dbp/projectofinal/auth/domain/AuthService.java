package com.dbp.projectofinal.auth.domain;

import com.dbp.projectofinal.auth.dto.RegisterReq;
import com.dbp.projectofinal.config.JwtService;
import com.dbp.projectofinal.usuario.domain.Category;
import com.dbp.projectofinal.usuario.domain.Role;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.infrastructure.BaseUserRepository;
import com.dbp.projectofinal.usuario.infrastructure.RoleRepository;
import org.e2e.e2e.auth.dto.JwtAuthResponse;
import org.e2e.e2e.auth.dto.LoginReq;
import org.e2e.e2e.auth.exceptions.UserAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final BaseUserRepository<Usuario> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthService(BaseUserRepository<Usuario> userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = new ModelMapper();
    }

    public void register(RegisterReq registerReq) {
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

        userRepository.save(usuario);
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