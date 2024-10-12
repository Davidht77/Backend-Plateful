package com.dbp.projectofinal.carta.application;

import com.dbp.projectofinal.carta.dto.CartaDTO;
import com.dbp.projectofinal.carta.dto.CreateCartaDTO;
import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.carta.domain.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @GetMapping
    public List<CartaDTO> getAllCartas() {
        return cartaService.getAllCartas().stream()
                .map(carta -> new CartaDTO(
                        carta.getId_carta(),
                        carta.getNombre(),
                        carta.getFecha_actualizacion(),
                        carta.getRestaurante().getNombre_restaurante()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaDTO> getCartaById(@PathVariable Long id) {
        Optional<Carta> optionalCarta = cartaService.getCartaById(id);
        if (optionalCarta.isPresent()) {
            Carta carta = optionalCarta.get();
            CartaDTO responseDTO = new CartaDTO(
                    carta.getId_carta(),
                    carta.getNombre(),
                    carta.getFecha_actualizacion(),
                    carta.getRestaurante().getNombre_restaurante());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CartaDTO createCarta(@RequestBody CreateCartaDTO requestDTO) {
        Carta carta = new Carta();
        carta.setNombre(requestDTO.getNombre());
        carta.setFecha_actualizacion(requestDTO.getFecha_actualizacion());
        carta.setRestaurante(requestDTO.getRestaurante());

        Carta savedCarta = cartaService.saveCarta(carta);
        return new CartaDTO(
                savedCarta.getId_carta(),
                savedCarta.getNombre(),
                savedCarta.getFecha_actualizacion(),
                savedCarta.getRestaurante().getNombre_restaurante());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarta(@PathVariable Long id) {
        Optional<Carta> optionalCarta = cartaService.getCartaById(id);
        if (optionalCarta.isPresent()) {
            cartaService.deleteCarta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
