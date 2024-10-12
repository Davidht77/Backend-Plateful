package com.dbp.projectofinal.plato.application;

import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.plato.dto.CreatePlatoDTO;
import com.dbp.projectofinal.plato.dto.PlatoDTO;
import com.dbp.projectofinal.plato.domain.PlatoService;
import com.dbp.projectofinal.plato.domain.Plato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> getAllPlatos() {
        List<PlatoDTO> platos = platoService.getAllPlatos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(platos);
    }

    @PostMapping
    public ResponseEntity<PlatoDTO> savePlato(@RequestBody CreatePlatoDTO createPlatoDTO) {
        Plato plato = new Plato();
        plato.setNombre(createPlatoDTO.getNombre());
        plato.setPrecio(createPlatoDTO.getPrecio());
        plato.setDisponibilidad(createPlatoDTO.getDisponibilidad());
        plato.setCarta(new Carta(createPlatoDTO.getId_carta()));

        Plato savedPlato = platoService.savePlato(plato);
        return ResponseEntity.ok(convertToDTO(savedPlato));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> getPlatoById(@PathVariable Long id) {
        Optional<Plato> platoOpt = platoService.getPlatoById(id);
        return platoOpt.map(plato -> ResponseEntity.ok(convertToDTO(plato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carta/{cartaId}")
    public ResponseEntity<List<PlatoDTO>> getPlatosByCartaId(@PathVariable Long cartaId) {
        List<PlatoDTO> platos = platoService.getPlatosByCartaId(cartaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<PlatoDTO>> getPlatosDisponibles() {
        List<PlatoDTO> platos = platoService.getPlatosDisponibles().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(platos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoDTO> updatePlato(@PathVariable Long id, @RequestBody CreatePlatoDTO createPlatoDTO) {
        Plato plato = new Plato();
        plato.setId_plato(id);
        plato.setNombre(createPlatoDTO.getNombre());
        plato.setPrecio(createPlatoDTO.getPrecio());
        plato.setDisponibilidad(createPlatoDTO.getDisponibilidad());
        plato.setCarta(new Carta(createPlatoDTO.getId_carta()));

        Plato updatedPlato = platoService.updatePlato(plato);
        return ResponseEntity.ok(convertToDTO(updatedPlato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlato(@PathVariable Long id) {
        platoService.deletePlato(id);
        return ResponseEntity.noContent().build();
    }

    private PlatoDTO convertToDTO(Plato plato) {
        PlatoDTO dto = new PlatoDTO();
        dto.setId_plato(plato.getId_plato());
        dto.setNombre(plato.getNombre());
        dto.setPrecio(plato.getPrecio());
        dto.setDisponibilidad(plato.getDisponibilidad());
        dto.setId_carta(plato.getCarta() != null ? plato.getCarta().getId_carta() : null);
        return dto;
    }
}
