package com.dbp.projectofinal.usuario.application;

import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.dto.CreateUsuarioDTO;
import com.dbp.projectofinal.usuario.dto.UsuarioDTO;
import com.dbp.projectofinal.usuario.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody CreateUsuarioDTO createUsuarioDTO) {
        Usuario usuario = usuarioService.saveUsuario(convertToEntity(createUsuarioDTO));
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

    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId_usuario(), usuario.getNombre(),
                usuario.getCorreo(), usuario.getTelefono());
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
