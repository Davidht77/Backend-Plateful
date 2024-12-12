package com.dbp.projectofinal.restaurante.application;


import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.CreateRestauranteDTO;
import com.dbp.projectofinal.restaurante.domain.RestauranteService;
import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.dto.UbiRequestDTO;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/nearby")
    public ResponseEntity<List<RestauranteResponseDTO>> getNearby1km(@RequestBody UbiRequestDTO ubiRequestDTO) throws IOException, InterruptedException, ApiException {
        List<RestauranteResponseDTO> lista = restauranteService.getNear(ubiRequestDTO);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<com.dbp.projectofinal.restaurante.dto.RestauranteDTO>> getAllRestaurantes() {
        List<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> restaurantes = restauranteService.getAllRestaurantes()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> getRestauranteById(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.getRestauranteById(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(convertToDTO(restaurante.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("//propietario/{id}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarPropietario(@PathVariable Long id){
        List<RestauranteResponseDTO> lista = restauranteService.porPropietario(id);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> saveRestaurante(@RequestBody CreateRestauranteDTO createRestauranteDTO) {
        Restaurante savedRestaurante = restauranteService.saveRestaurante(createRestauranteDTO);
        return ResponseEntity.created(null).body(convertToDTO(savedRestaurante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> reemplazar(@PathVariable Long id, @RequestBody CreateRestauranteDTO createRestauranteDTO){
        Restaurante savedRestaurante = restauranteService.reemplaze(id,createRestauranteDTO);
        return ResponseEntity.ok(convertToDTO(savedRestaurante));
    }

    @PatchMapping("/propietario/{id}")
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> actualizarPropietario(@PathVariable Long id, @PathVariable String email){
        Restaurante savedRestaurante = restauranteService.actualizar(id,email);
        return ResponseEntity.ok(convertToDTO(savedRestaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    private com.dbp.projectofinal.restaurante.dto.RestauranteDTO convertToDTO(Restaurante restaurante) {
        com.dbp.projectofinal.restaurante.dto.RestauranteDTO dto = new com.dbp.projectofinal.restaurante.dto.RestauranteDTO();
        dto.setId_restaurante(restaurante.getId_restaurante());
        dto.setNombre_restaurante(restaurante.getNombre_restaurante());
        dto.setHorario(restaurante.getHorario());
        dto.setTipoRestaurante(restaurante.getTipoRestaurante());
        dto.setPropietarioId(restaurante.getPropietario().getId_usuario());  // Propietario ID
        dto.setCartaId(restaurante.getCarta().getId_carta());                // Carta ID
        dto.setCalificacion_promedio(restaurante.getCalificacion_promedio());
        dto.setUbicacionId(restaurante.getUbicacion().getId_ubicacion());    // Ubicación ID
        dto.setLatitude(restaurante.getUbicacion().getLatitud());
        dto.setLongitude(restaurante.getUbicacion().getLongitud());
        return dto;
    }
}
