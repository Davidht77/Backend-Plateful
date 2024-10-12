package com.dbp.projectofinal.restaurante.domain;

import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.dto.UbiRequestDTO;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.ubicacion.domain.UbicacionService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UbicacionService ubicacionService;

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

    public List<RestauranteResponseDTO> getNear(UbiRequestDTO requestDTO) throws IOException, InterruptedException, ApiException {
        Double lat = requestDTO.getLatitud();
        Double lng = requestDTO.getLongitud();
        return ubicacionService.getNearRestaurantUbication(lat,lng);
    }
}
