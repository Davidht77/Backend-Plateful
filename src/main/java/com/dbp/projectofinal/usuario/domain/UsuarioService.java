package com.dbp.projectofinal.usuario.domain;
import com.dbp.projectofinal.exceptions.UsuarioNotFoundException;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.propietario.infrastructure.PropietarioRepository;
import com.dbp.projectofinal.usuario.dto.CreateUsuarioDTO;
import com.dbp.projectofinal.usuario.dto.UserNewPassword;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario reemplazar(Long id, CreateUsuarioDTO createUsuarioDTO){
        Optional<Usuario> usuario = getUsuarioById(id);
        if (usuario.isEmpty())
            throw new UsuarioNotFoundException("");
        Usuario newusuario = convertToEntity(createUsuarioDTO);
        newusuario.setUbicacion(usuario.get().getUbicacion());
        newusuario.setId_usuario(usuario.get().getId_usuario());
        deleteUsuario(usuario.get().getId_usuario());
        usuarioRepository.save(newusuario);
        return newusuario;
    }

    public Optional<Usuario> findbyEmail(String email){
        return usuarioRepository.findByEmail(email);
    }


    public Usuario changePassword(UserNewPassword userNewPassword){
        Optional<Usuario> usuario = findbyEmail(userNewPassword.getEmail());
        if (usuario.isEmpty())
            throw new UsuarioNotFoundException("");
        usuario.get().setPassword(userNewPassword.getPassword());
        saveUsuario(usuario.get());
        return usuario.get();
    }

//    public Usuario findByEmail(String username, String role) {
//        Usuario user;
//        if (role.equals("ROLE_PROPIETARIO"))
//            user = PropietarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        else
//            user = usuarioRepository.findByEmail(username).orElseThrow(() -> new UnauthorizeOperationException("User not found"));
//
//        return user;
//    }
//    @Bean(name = "UserDetailsService")
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            Usuario user = userRepository
//                    .findByEmail(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            return (UserDetails) user;
//        };
//    }

    private Usuario convertToEntity(CreateUsuarioDTO createUsuarioDTO) {
        return new Usuario(createUsuarioDTO.getNombre(),
                createUsuarioDTO.getCorreo(),
                createUsuarioDTO.getPassword(),
                createUsuarioDTO.getTelefono(),
                createUsuarioDTO.getFechaNacimiento(),
                null);
    }
}