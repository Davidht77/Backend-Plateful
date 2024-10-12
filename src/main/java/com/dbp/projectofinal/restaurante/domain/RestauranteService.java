package com.dbp.projectofinal.restaurante.domain;

import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.exceptions.PropietarioNotFoundException;
import com.dbp.projectofinal.exceptions.RestauranteNotFoundException;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.propietario.infrastructure.PropietarioRepository;
import com.dbp.projectofinal.restaurante.dto.CreateRestauranteDTO;
import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.dto.UbiRequestDTO;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.ubicacion.domain.UbicacionService;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<RestauranteDTO> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Optional<RestauranteDTO> getRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    public RestauranteDTO saveRestaurante(CreateRestauranteDTO createRestauranteDTO) {
        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setNombre_restaurante(createRestauranteDTO.getNombre_restaurante());
        restaurante.setHorario(createRestauranteDTO.getHorario());
        restaurante.setTipoRestaurante(createRestauranteDTO.getTipoRestaurante());
        restaurante.setPropietario(new Propietario(createRestauranteDTO.getPropietarioId()));
        restaurante.setCarta(new Carta(createRestauranteDTO.getCartaId()));
        restaurante.setCalificacion_promedio(createRestauranteDTO.getCalificacion_promedio());
        restaurante.setUbicacion(new Ubicacion(createRestauranteDTO.getUbicacionId()));
        return restauranteRepository.save(restaurante);
    }

    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }

    public RestauranteDTO reemplaze(Long id , CreateRestauranteDTO createRestauranteDTO){
        Optional<RestauranteDTO> restaurante2 = restauranteRepository.findById(id);
        if(restaurante2.isEmpty())
            throw new RestauranteNotFoundException("");
        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setId_restaurante(restaurante2.get().getId_restaurante());
        restaurante.setNombre_restaurante(createRestauranteDTO.getNombre_restaurante());
        restaurante.setHorario(createRestauranteDTO.getHorario());
        restaurante.setTipoRestaurante(createRestauranteDTO.getTipoRestaurante());
        restaurante.setPropietario(new Propietario(createRestauranteDTO.getPropietarioId()));
        restaurante.setCarta(new Carta(createRestauranteDTO.getCartaId()));
        restaurante.setCalificacion_promedio(createRestauranteDTO.getCalificacion_promedio());
        restaurante.setUbicacion(new Ubicacion(createRestauranteDTO.getUbicacionId()));
        deleteRestaurante(restaurante2.get().getId_restaurante());
        restauranteRepository.save(restaurante);
        return restaurante;
    }

    public RestauranteDTO actualizar(Long id, String email){
        Optional<RestauranteDTO> restaurante2 = restauranteRepository.findById(id);
        if(restaurante2.isEmpty())
            throw new RestauranteNotFoundException("");
        Optional<Propietario> propietario = propietarioRepository.findByEmail(email);
        if(propietario.isEmpty())
            throw new PropietarioNotFoundException("");
        restaurante2.get().setPropietario(propietario.get());
        restauranteRepository.save(restaurante2.get());
        return restaurante2.get();
    }

    public List<RestauranteResponseDTO> porPropietario(Long id){
        Optional<Propietario> propietario = propietarioRepository.findById(id);
        if(propietario.isEmpty())
            throw new PropietarioNotFoundException("");
        List<RestauranteDTO> restaurantes = propietario.get().getRestaurantes();
        List<RestauranteResponseDTO> newRestaurantes = new ArrayList<>();
        for(RestauranteDTO restaurante: restaurantes){
            RestauranteResponseDTO restauranteResponseDTO = new RestauranteResponseDTO();
            restauranteResponseDTO.setNombre_restaurante(restaurante.getNombre_restaurante());
            restauranteResponseDTO.setHorario(restaurante.getHorario());
            restauranteResponseDTO.setTipoRestaurante(restaurante.getTipoRestaurante());
            restauranteResponseDTO.setCalificacion_promedio(restaurante.getCalificacion_promedio());
            Ubicacion ubicacion = restaurante.getUbicacion();
            restauranteResponseDTO.setUbicacion(new UbicacionResponseDTO(ubicacion.getCiudad(),ubicacion.getDireccionCompleta()
                    ,ubicacion.getLongitud(),ubicacion.getLatitud(),ubicacion.getCodigoPostal()));
        }
        return newRestaurantes;
    }

    public List<RestauranteResponseDTO> getNear(UbiRequestDTO requestDTO) throws IOException, InterruptedException, ApiException {
        Double lat = requestDTO.getLatitud();
        Double lng = requestDTO.getLongitud();
        return ubicacionService.getNearRestaurantUbication(lat,lng);
    }
}
