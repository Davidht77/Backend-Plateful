package com.dbp.projectofinal.ubicacion.controller;

import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.ubicacion.dto.CreateUbicacionDTO;
import com.dbp.projectofinal.ubicacion.dto.UbicacionDTO;
import com.dbp.projectofinal.ubicacion.domain.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> getAllUbicaciones() {
        List<UbicacionDTO> ubicaciones = ubicacionService.getAllUbicaciones()
                .stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(ubicaciones);
    }

    @PostMapping
    public ResponseEntity<UbicacionDTO> saveUbicacion(@RequestBody CreateUbicacionDTO createUbicacionDTO) {
        Ubicacion ubicacion = ubicacionService.saveUbicacion(convertToEntity(createUbicacionDTO));
        return ResponseEntity.ok(convertToDTO(ubicacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getUbicacionById(@PathVariable Long id) {
        Ubicacion ubicacion = ubicacionService.getUbicacionById(id)
                .orElseThrow(() -> new RuntimeException("Ubicacion not found"));
        return ResponseEntity.ok(convertToDTO(ubicacion));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> updateUbicacion(@PathVariable Long id, @RequestBody CreateUbicacionDTO createUbicacionDTO) {
        Ubicacion updatedUbicacion = ubicacionService.updateUbicacion(id, convertToEntity(createUbicacionDTO))
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada"));
        return ResponseEntity.ok(convertToDTO(updatedUbicacion));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }

    private UbicacionDTO convertToDTO(Ubicacion ubicacion) {
        return new UbicacionDTO(ubicacion.getCiudad(),
                ubicacion.getDireccionCompleta(), ubicacion.getLongitud(),
                ubicacion.getLatitud());
    }

    private Ubicacion convertToEntity(CreateUbicacionDTO createUbicacionDTO) {
        return new Ubicacion(createUbicacionDTO.getCiudad(),
                createUbicacionDTO.getDireccionCompleta(),
                createUbicacionDTO.getLongitud(), createUbicacionDTO.getLatitud());
    }
}
