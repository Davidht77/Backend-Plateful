package com.dbp.projectofinal.carta.domain;

import com.dbp.projectofinal.carta.dto.CartaDTO;
import com.dbp.projectofinal.carta.dto.CreateCartaDTO;
import com.dbp.projectofinal.carta.infrastructure.CartaRepository;
import com.dbp.projectofinal.exceptions.CartaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    public List<Carta> getAllCartas() {
        return cartaRepository.findAll();
    }

    public Optional<Carta> getCartaById(Long id) {
        return cartaRepository.findById(id);
    }

    public Carta saveCarta(Carta carta) {
        return cartaRepository.save(carta);
    }

    public void deleteCarta(Long id) {
        cartaRepository.deleteById(id);
    }

    public CartaDTO reemplazar(Long id, CreateCartaDTO createCartaDTO){
        Optional<Carta> carta = cartaRepository.findById(id);
        if(carta.isEmpty())
            throw new CartaNotFoundException(id);
        Carta newcarta = new Carta();
        newcarta.setId_carta(carta.get().getId_carta());
        newcarta.setNombre(createCartaDTO.getNombre());
        newcarta.setFecha_actualizacion(createCartaDTO.getFecha_actualizacion());
        cartaRepository.deleteById(carta.get().getId_carta());
        cartaRepository.save(newcarta);
        return new CartaDTO(newcarta.getId_carta(),newcarta.getNombre(),newcarta.getFecha_actualizacion(),newcarta.getRestaurante().getNombre_restaurante());
    }


}
