package com.dbp.projectofinal.usuario.application;

import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.dto.CreateUsuarioDTO;
import com.dbp.projectofinal.usuario.dto.UserNewPassword;
import com.dbp.projectofinal.usuario.dto.UsuarioDTO;
import com.dbp.projectofinal.usuario.domain.UsuarioService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios()
                .stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<String> verifyroles(@PathVariable Long id) {
        usuarioService.verificarRoles(id);
        return ResponseEntity.ok("Roles verificados");
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getOwn() {
        Usuario user = usuarioService.getOwnSelf();
        return ResponseEntity.ok(convertToDTO(user));
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
        Usuario usuario = usuarioService.saveUsuario(convertToEntity(createUsuarioDTO));
        return ResponseEntity.created(null).body(convertToDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> reemplazar(@PathVariable Long id, @RequestBody CreateUsuarioDTO createUsuarioDTO){
        Usuario usuario = usuarioService.reemplazar(id,createUsuarioDTO);
        return ResponseEntity.ok(convertToDTO(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found"));
        return ResponseEntity.ok(convertToDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> cambiarcontrasena(@RequestBody UserNewPassword newuser) {
        Usuario  usuario = usuarioService.changePassword(newuser);
        return ResponseEntity.ok(convertToDTO(usuario));
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId_usuario(), usuario.getNombre(),
                usuario.getEmail(), usuario.getTelefono());
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
