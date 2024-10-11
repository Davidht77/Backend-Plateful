package com.dbp.projectofinal.comentario.controller;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}
