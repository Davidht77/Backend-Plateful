package com.dbp.projectofinal.resena.controller;

import com.dbp.projectofinal.resena.domain.ResenaService;
import com.dbp.projectofinal.resena.dto.CreateResenaDTO;
import com.dbp.projectofinal.resena.dto.ResenaDTO;
import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> getAllResenas() {
        List<ResenaDTO> resenas = resenaService.getAllResenas()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> getResenaById(@PathVariable Long id) {
        Resena resena = resenaService.getResenaById(id)
                .orElseThrow(() -> new RuntimeException("Resena no encontrada"));
        return ResponseEntity.ok(convertToDTO(resena));
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> createResena(@RequestBody CreateResenaDTO createResenaDTO) {
        Resena resena = new Resena();
        resena.setCalificacion(createResenaDTO.getCalificacion());
        resena.setUsuario(new Usuario(createResenaDTO.getId_usuario()));
        resena.setRestaurante(new Restaurante(createResenaDTO.getId_restaurante()));

        Resena savedResena = resenaService.saveResena(resena);
        return ResponseEntity.ok(convertToDTO(savedResena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        resenaService.deleteResena(id);
        return ResponseEntity.noContent().build();
    }

    private ResenaDTO convertToDTO(Resena resena) {
        ResenaDTO dto = new ResenaDTO();
        dto.setId_resena(resena.getId_resena());
        dto.setCalificacion(resena.getCalificacion());
        dto.setId_usuario(resena.getUsuario().getId_usuario());
        dto.setId_restaurante(resena.getRestaurante().getId_restaurante());
        dto.setFecha(resena.getFecha().toString());
        return dto;
    }
}
