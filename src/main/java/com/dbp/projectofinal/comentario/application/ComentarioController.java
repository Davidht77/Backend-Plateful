package com.dbp.projectofinal.comentario.application;

import com.dbp.projectofinal.comentario.dto.CreateComentarioDTO;
import com.dbp.projectofinal.comentario.dto.ComentarioDTO;
import com.dbp.projectofinal.comentario.domain.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getAllComentarios() {
        List<ComentarioDTO> comentarios = comentarioService.getAllComentarios();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> saveComentario(@RequestBody CreateComentarioDTO createComentarioDTO) {
        ComentarioDTO comentario = comentarioService.saveComentario(createComentarioDTO);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        ComentarioDTO comentario = comentarioService.getComentarioById(id);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/resena/{resenaId}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByResenaId(@PathVariable Long resenaId) {
        List<ComentarioDTO> comentarios = comentarioService.getComentariosByResenaId(resenaId);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByUsuarioId(@PathVariable Long usuarioId) {
        List<ComentarioDTO> comentarios = comentarioService.getComentariosByUsuarioId(usuarioId);
        return ResponseEntity.ok(comentarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id, @RequestBody CreateComentarioDTO createComentarioDTO) {
        ComentarioDTO comentario = comentarioService.updateComentario(id, createComentarioDTO);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}
