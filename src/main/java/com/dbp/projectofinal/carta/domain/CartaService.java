package com.dbp.projectofinal.carta.domain;

import com.dbp.projectofinal.carta.dto.CartaDTO;
import com.dbp.projectofinal.carta.dto.CreateCartaDTO;
import com.dbp.projectofinal.carta.infrastructure.CartaRepository;
import com.dbp.projectofinal.exceptions.CartaNotFoundException;
import com.dbp.projectofinal.exceptions.RestauranteNotFoundException;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Carta> getAllCartas() {
        return cartaRepository.findAll();
    }

    public Optional<Carta> getCartaById(Long id) {
        return cartaRepository.findById(id);
    }

    public Carta saveCarta(CreateCartaDTO requestDTO) {
        Carta carta = new Carta();
        carta.setNombre(requestDTO.getNombre());
        carta.setFecha_actualizacion(requestDTO.getFecha_actualizacion());
        Optional<Restaurante> restaurante = restauranteRepository.findById(requestDTO.getRestauranteId());
        if(restaurante.isEmpty())
            throw new RestauranteNotFoundException("");
        carta.setRestaurante(restaurante.get());
        return cartaRepository.save(carta);
    }

    public void deleteCarta(Long id) {
        cartaRepository.deleteById(id);
    }

    public CartaDTO byRestaurante(Long id){
        Optional<Restaurante> restaurante1 = restauranteRepository.findById(id);
        if (restaurante1.isEmpty())
            throw new RestauranteNotFoundException("");
        Carta carta  = restaurante1.get().getCarta();
        return new CartaDTO(
                carta.getId_carta(),carta.getNombre(),
                carta.getFecha_actualizacion(),restaurante1.get().getNombre_restaurante());
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
