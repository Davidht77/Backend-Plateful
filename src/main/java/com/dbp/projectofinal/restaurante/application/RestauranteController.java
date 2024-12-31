package com.dbp.projectofinal.restaurante.application;


import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.*;
import com.dbp.projectofinal.restaurante.domain.RestauranteService;
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

    @GetMapping("/nearby2")
    public ResponseEntity<List<RestauranteResponseDTO>> getNearby1km(@RequestBody UbiRequestDTO ubiRequestDTO) throws IOException, InterruptedException, ApiException {
        List<RestauranteResponseDTO> lista = restauranteService.getNear(ubiRequestDTO);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<RestauranteResponseDTO>> getNearbyyy1km(
            @RequestParam Double latitud,
            @RequestParam Double longitud
    ) throws IOException, InterruptedException, ApiException {
        UbiRequestDTO ubiRequestDTO = new UbiRequestDTO(longitud,latitud);
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

    @GetMapping("/tipo")
    public ResponseEntity<List<RestauranteResponseDTO>> getTipo(@RequestParam String tipo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RestauranteResponseDTO> restaurantes = restauranteService.getRestaurantesPorTipo(tipo, page, size)
                .map(this::convertResponse)
                .getContent();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<RestauranteResponseDTO>> getbyNombre(@RequestParam String nombre,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        List<RestauranteResponseDTO> restaurantes = restauranteService.getRestaurantePorNombre(nombre, page, size).stream()
                .map(this::convertResponse).toList();
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

    @PatchMapping("/edit/{id}")
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> actualizarPropietario(@PathVariable Long id, @RequestBody EditRestaurantDTO editRestaurantDTO) throws IOException, InterruptedException, ApiException {
        Restaurante savedRestaurante = restauranteService.actualizar(id,editRestaurantDTO);
        return ResponseEntity.ok(convertToDTO(savedRestaurante));
    }

    @PatchMapping("/carta/{id_rest}/{id_carta}")
    public ResponseEntity<com.dbp.projectofinal.restaurante.dto.RestauranteDTO> actualizarcarta(@PathVariable Long id_rest, @PathVariable Long id_carta){
        Restaurante savedRestaurante = restauranteService.actualizarCarta(id_rest,id_carta);
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
        com.dbp.projectofinal.restaurante.dto.RestauranteDTO dto = new RestauranteDTO();
        dto.setId_restaurante(restaurante.getId_restaurante());
        dto.setNombre_restaurante(restaurante.getNombre_restaurante());
        dto.setHorario(restaurante.getHorario());
        dto.setTipoRestaurante(restaurante.getTipoRestaurante());
        dto.setPropietarioId(restaurante.getPropietario().getId_usuario());
        dto.setNombre_propietario(restaurante.getPropietario().getNombre());// Propietario ID
        if (restaurante.getCarta() != null) {
            dto.setCartaId(restaurante.getCarta().getId_carta());
        }
        if (restaurante.getCarta() != null) {
            dto.setNombre_carta(restaurante.getCarta().getNombre());
        }
        dto.setCalificacion_promedio(restaurante.getCalificacion_promedio());
        dto.setDireccion(restaurante.getUbicacion().getDireccionCompleta());    // Ubicación ID
        dto.setLatitude(restaurante.getUbicacion().getLatitud());
        dto.setLongitude(restaurante.getUbicacion().getLongitud());
        return dto;
    }
}
