package com.dbp.projectofinal.auth.utils;

import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUtils {

    @Autowired
    private UsuarioService userService ;

//    public boolean isAdminOrResourceOwner(Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userdetails = (UserDetails) authentication.getPrincipal();
//        String username = userdetails.getUsername();
//        String role = userdetails.getAuthorities().toArray()[0].toString();
//        Usuario passenger= userService.findByEmail(username, role);
//
//        return passenger.getId().equals(id) || passenger.getRole().equals(Role.ADMIN);
//    }
//
//    public boolean getCurrentUserEmail() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return false;
//        }
//
//        return true;
//    }
}