package com.dbp.projectofinal.restaurante.controller;


import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.CreateRestauranteDTO;
import com.dbp.projectofinal.restaurante.dto.RestauranteDTO;
import com.dbp.projectofinal.restaurante.domain.RestauranteService;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.ubicacion.Ubicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> getAllRestaurantes() {
        List<RestauranteDTO> restaurantes = restauranteService.getAllRestaurantes()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.getRestauranteById(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(convertToDTO(restaurante.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> saveRestaurante(@RequestBody CreateRestauranteDTO createRestauranteDTO) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre_restaurante(createRestauranteDTO.getNombre_restaurante());
        restaurante.setHorario(createRestauranteDTO.getHorario());
        restaurante.setTipoRestaurante(createRestauranteDTO.getTipoRestaurante());
        restaurante.setPropietario(new Propietario(createRestauranteDTO.getPropietarioId()));
        restaurante.setCarta(new Carta(createRestauranteDTO.getCartaId()));
        restaurante.setCalificacion_promedio(createRestauranteDTO.getCalificacion_promedio());
        restaurante.setUbicacion(new Ubicacion(createRestauranteDTO.getUbicacionId()));

        Restaurante savedRestaurante = restauranteService.saveRestaurante(restaurante);
        return ResponseEntity.ok(convertToDTO(savedRestaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    private RestauranteDTO convertToDTO(Restaurante restaurante) {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId_restaurante(restaurante.getId_restaurante());
        dto.setNombre_restaurante(restaurante.getNombre_restaurante());
        dto.setHorario(restaurante.getHorario());
        dto.setTipoRestaurante(restaurante.getTipoRestaurante());
        dto.setPropietarioId(restaurante.getPropietario().getId_usuario());  // Propietario ID
        dto.setCartaId(restaurante.getCarta().getId_carta());                // Carta ID
        dto.setCalificacion_promedio(restaurante.getCalificacion_promedio());
        dto.setUbicacionId(restaurante.getUbicacion().getId_ubicacion());    // Ubicación ID
        return dto;
    }
}
