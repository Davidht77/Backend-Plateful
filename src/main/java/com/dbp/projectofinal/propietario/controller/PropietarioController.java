package com.dbp.projectofinal.propietario.controller;

import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.propietario.dto.CreatePropietarioDTO;
import com.dbp.projectofinal.propietario.dto.PropietarioDTO;
import com.dbp.projectofinal.propietario.domain.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> getAllPropietarios() {
        List<PropietarioDTO> propietarios = propietarioService.getAllPropietarios()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioDTO> getPropietarioById(@PathVariable Long id) {
        Propietario propietario = propietarioService.getPropietarioById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        return ResponseEntity.ok(convertToDTO(propietario));
    }

    @PostMapping
    public ResponseEntity<PropietarioDTO> savePropietario(@RequestBody CreatePropietarioDTO createPropietarioDTO) {
        Propietario propietario = new Propietario();
        propietario.setNombre(createPropietarioDTO.getNombre());
        propietario.setCorreo(createPropietarioDTO.getCorreo());
        propietario.setTipoDocumento(createPropietarioDTO.getTipoDocumento());
        propietario.setNumeroDocumento(createPropietarioDTO.getNumeroDocumento());
        propietario.setFotoPerfil(createPropietarioDTO.getFotoPerfil());

        Propietario savedPropietario = propietarioService.savePropietario(propietario);
        return ResponseEntity.ok(convertToDTO(savedPropietario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropietario(@PathVariable Long id) {
        propietarioService.deletePropietario(id);
        return ResponseEntity.noContent().build();
    }

    private PropietarioDTO convertToDTO(Propietario propietario) {
        PropietarioDTO dto = new PropietarioDTO();
        dto.setId_usuario(propietario.getId_usuario());
        dto.setNombre(propietario.getNombre());
        dto.setCorreo(propietario.getCorreo());
        dto.setTipoDocumento(propietario.getTipoDocumento());
        dto.setNumeroDocumento(propietario.getNumeroDocumento());
        dto.setFotoPerfil(propietario.getFotoPerfil());

        List<Long> restauranteIds = propietario.getRestaurantes()
                .stream()
                .map(restaurante -> restaurante.getId_restaurante())
                .collect(Collectors.toList());
        dto.setRestauranteIds(restauranteIds);

        return dto;
    }
}
