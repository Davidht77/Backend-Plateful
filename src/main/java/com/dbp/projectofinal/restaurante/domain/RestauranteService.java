package com.dbp.projectofinal.restaurante.domain;

import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> getRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante saveRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
