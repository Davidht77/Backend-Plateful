package com.dbp.projectofinal.config;

import com.dbp.projectofinal.usuario.domain.Role;
import com.dbp.projectofinal.usuario.infrastructure.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository){
        return args ->{
            if (roleRepository.findByName("PROPIETARIO").isEmpty()){
                roleRepository.save(new Role(null, "PROPIETARIO"));
            }
            if (roleRepository.findByName("CLIENTE").isEmpty()){
                roleRepository.save(new Role(null,"CLIENTE"));
            }
        };
    }
}
