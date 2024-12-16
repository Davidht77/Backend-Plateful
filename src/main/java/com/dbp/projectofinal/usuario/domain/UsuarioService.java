package com.dbp.projectofinal.usuario.domain;
import com.dbp.projectofinal.auth.utils.AuthorizationUtils;
import com.dbp.projectofinal.eventos.SendMailEvent;
import com.dbp.projectofinal.exceptions.PropietarioNotFoundException;
import com.dbp.projectofinal.exceptions.UsuarioNotFoundException;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.propietario.infrastructure.PropietarioRepository;
import com.dbp.projectofinal.usuario.dto.CreateUsuarioDTO;
import com.dbp.projectofinal.usuario.dto.UserNewPassword;
import com.dbp.projectofinal.usuario.infrastructure.RoleRepository;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    @Lazy
    private AuthorizationUtils authorizationUtils;


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Role> obtenerRoles(Long id){
        Role propietarioRole = roleRepository.findByName("ROLE_PROPIETARIO")
                .orElseThrow(() -> new RuntimeException("Error: Role PROPIETARIO is not found."));
        Role clienteRole = roleRepository.findByName("ROLE_CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Role CLIENTE is not found."));

        List<Object[]> results = usuarioRepository.findUsuarioWithRoles(id);
        List<Role> lista = new ArrayList<>();
        for (Object[] row : results) {
            Object palabra= row[row.length-1];
            String palabrastring = palabra.toString();
            if (palabrastring.equals("ROLE_PROPIETARIO")) {
                lista.add(propietarioRole);
            } else if (palabrastring.equals("ROLE_CLIENTE")){
                lista.add(clienteRole);
            }
        }
        return lista;
    }

    public void verificarRoles(Long id) {
        List<Object[]> results = usuarioRepository.findUsuarioWithRoles(id);

        for (Object[] row : results) {
            System.out.println("Usuario: " + row[5]);
            System.out.println("Role Name: " + row[row.length-1]);
        }
    }

    public Usuario getOwnSelf(){
        String email = authorizationUtils.getCurrentUserEmail();
        if (email == null)
            throw new UsuarioNotFoundException("Anonymous User not allowed to access this resource");

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty())
            throw new UsuarioNotFoundException("No se encontro el usuario");

        return usuario.get();
    }

    public Usuario saveUsuario(Usuario usuario) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("to",usuario.getEmail());
//        map.put("name",usuario.getNombre());
//        applicationEventPublisher.publishEvent(new SendMailEvent(map) );
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



    private Usuario convertToEntity(CreateUsuarioDTO createUsuarioDTO) {
        return new Usuario(createUsuarioDTO.getNombre(),
                createUsuarioDTO.getCorreo(),
                createUsuarioDTO.getPassword(),
                createUsuarioDTO.getTelefono(),
                createUsuarioDTO.getFechaNacimiento(),
                null);
    }
}