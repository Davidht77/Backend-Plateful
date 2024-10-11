package com.dbp.projectofinal.carta.domain;

import com.dbp.projectofinal.carta.infrastructure.CartaRepository;
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
}
