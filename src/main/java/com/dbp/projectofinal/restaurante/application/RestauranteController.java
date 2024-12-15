package com.dbp.projectofinal.restaurante.application;


import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.CreateRestauranteDTO;
import com.dbp.projectofinal.restaurante.domain.RestauranteService;
import com.dbp.projectofinal.restaurante.dto.RestauranteDTO;
import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.dto.UbiRequestDTO;
import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
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
    public ResponseEntity<List<RestauranteResponseDTO>> getAllRestaurantes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RestauranteResponseDTO> restaurantes = restauranteService.getAllRestaurantesPaginated(page, size)
                .map(this::convertResponse)
                .getContent();
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

    @GetMapping("/propietario/{id}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarPropietario(@PathVariable Long id){
        List<RestauranteResponseDTO> lista = restauranteService.porPropietario(id);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/create")
    public ResponseEntity<RestauranteResponseDTO> saveRestaurante(@RequestBody CreateRestauranteDTO createRestauranteDTO) throws IOException, InterruptedException, ApiException {
        Restaurante savedRestaurante = restauranteService.saveRestaurante(createRestauranteDTO);
        return ResponseEntity.created(null).body(convertResponse(savedRestaurante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> reemplazar(@PathVariable Long id, @RequestBody CreateRestauranteDTO createRestauranteDTO) throws IOException, InterruptedException, ApiException {
        Restaurante savedRestaurante = restauranteService.reemplaze(id,createRestauranteDTO);
        return ResponseEntity.ok(convertResponse(savedRestaurante));
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

    private RestauranteResponseDTO convertResponse(Restaurante restaurante){
        RestauranteResponseDTO restauranteResponseDTO = new RestauranteResponseDTO();
        restauranteResponseDTO.setId(restaurante.getId_restaurante());
        restauranteResponseDTO.setNombre_restaurante(restaurante.getNombre_restaurante());
        restauranteResponseDTO.setHorario(restaurante.getHorario());
        restauranteResponseDTO.setTipoRestaurante(restaurante.getTipoRestaurante());
        restauranteResponseDTO.setCalificacion_promedio(restaurante.getCalificacion_promedio());
        Ubicacion ubicacion = restaurante.getUbicacion();
        restauranteResponseDTO.setUbicacion(new UbicacionResponseDTO(ubicacion.getCiudad(),ubicacion.getDireccionCompleta()
                ,ubicacion.getLongitud(),ubicacion.getLatitud(),ubicacion.getCodigoPostal()));
        return restauranteResponseDTO;
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
