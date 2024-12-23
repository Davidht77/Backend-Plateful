package com.dbp.projectofinal.restaurante.domain;

import com.dbp.projectofinal.auth.utils.AuthorizationUtils;
import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.carta.infrastructure.CartaRepository;
import com.dbp.projectofinal.exceptions.CartaNotFoundException;
import com.dbp.projectofinal.exceptions.PropietarioNotFoundException;
import com.dbp.projectofinal.exceptions.RestauranteNotFoundException;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.propietario.infrastructure.PropietarioRepository;
import com.dbp.projectofinal.restaurante.dto.CreateRestauranteDTO;
import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.dto.UbiRequestDTO;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.ubicacion.controller.UbicacionController;
import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import com.dbp.projectofinal.ubicacion.domain.UbicacionService;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private AuthorizationUtils authorizationUtils;
    @Autowired
    private CartaRepository cartaRepository;

    public Page<Restaurante> getAllRestaurantesPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return restauranteRepository.findAll(pageRequest);
    }

    public Page<Restaurante> getRestaurantesPorTipo(String Tipo,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return restauranteRepository.findRestaurantesByTipoRestauranteContainingIgnoreCase(Tipo,pageRequest);
    }

    public Optional<Restaurante> getRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante saveRestaurante(CreateRestauranteDTO createRestauranteDTO) throws IOException, InterruptedException, ApiException {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre_restaurante(createRestauranteDTO.getNombre_restaurante());
        restaurante.setHorario(createRestauranteDTO.getHorario());
        restaurante.setTipoRestaurante(createRestauranteDTO.getTipoRestaurante());
        Optional<Propietario> propietario = propietarioRepository.findByEmail(createRestauranteDTO.getEmail());
        if (propietario.isEmpty())
            throw new PropietarioNotFoundException("No se encontro el propietario");

        restaurante.setPropietario(propietario.get());
        restaurante.setCalificacion_promedio(0.0);

        Ubicacion ubi =  ubicacionService.findAndsaveUbication(createRestauranteDTO.getDireccion());
        restaurante.setUbicacion(ubi);

        Restaurante restaurante2 = restauranteRepository.save(restaurante);

        propietario.get().getRestaurantes().add(restaurante2);

        propietarioRepository.save(propietario.get());
        return restaurante2;
    }

    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }

    public Restaurante reemplaze(Long id , CreateRestauranteDTO createRestauranteDTO) throws IOException, InterruptedException, ApiException {
        Optional<Restaurante> restaurante2 = restauranteRepository.findById(id);
        if(restaurante2.isEmpty())
            throw new RestauranteNotFoundException("");
        Restaurante restaurante = new Restaurante();
        restaurante.setId_restaurante(restaurante2.get().getId_restaurante());
        restaurante.setNombre_restaurante(createRestauranteDTO.getNombre_restaurante());
        restaurante.setHorario(createRestauranteDTO.getHorario());
        restaurante.setTipoRestaurante(createRestauranteDTO.getTipoRestaurante());
        Optional<Propietario> propietario = propietarioRepository.findByEmail(createRestauranteDTO.getEmail());
        if (propietario.isEmpty())
            throw new PropietarioNotFoundException("No se encontro el propietario");

        restaurante.setPropietario(propietario.get());

        restaurante.setCalificacion_promedio(0.0);

        Ubicacion ubi =  ubicacionService.findAndsaveUbication(createRestauranteDTO.getDireccion());
        restaurante.setUbicacion(ubi);
        deleteRestaurante(restaurante2.get().getId_restaurante());
        restauranteRepository.save(restaurante);
        return restaurante;
    }

    public Restaurante actualizar(Long id, String email){
        Optional<Restaurante> restaurante2 = restauranteRepository.findById(id);
        if(restaurante2.isEmpty())
            throw new RestauranteNotFoundException("");
        Optional<Propietario> propietario = propietarioRepository.findByEmail(email);
        if(propietario.isEmpty())
            throw new PropietarioNotFoundException("");
        restaurante2.get().setPropietario(propietario.get());
        restauranteRepository.save(restaurante2.get());
        return restaurante2.get();
    }

    public Restaurante actualizarCarta(Long id_rest, Long id_carta){
        Optional<Restaurante> restaurante2 = restauranteRepository.findById(id_rest);
        if(restaurante2.isEmpty())
            throw new RestauranteNotFoundException("");
        Optional<Carta> carta = cartaRepository.findById(id_carta);
        if(carta.isEmpty())
            throw new CartaNotFoundException(id_carta);
        restaurante2.get().setCarta(carta.get());
        restauranteRepository.save(restaurante2.get());
        return restaurante2.get();
    }

    public List<RestauranteResponseDTO> porPropietario(Long id) {
        Optional<Propietario> propietarioOpt = propietarioRepository.findById(id);
        if (propietarioOpt.isEmpty()) {
            throw new PropietarioNotFoundException("No se encontró el propietario");
        }

        // Forzar la inicialización de la lista de restaurantes
        List<Restaurante> restaurantes = restauranteRepository.findByPropietarioId(id);

        List<RestauranteResponseDTO> newRestaurantes = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            RestauranteResponseDTO restauranteResponseDTO = new RestauranteResponseDTO();
            restauranteResponseDTO.setId(restaurante.getId_restaurante());
            restauranteResponseDTO.setNombre_restaurante(restaurante.getNombre_restaurante());
            restauranteResponseDTO.setHorario(restaurante.getHorario());
            restauranteResponseDTO.setTipoRestaurante(restaurante.getTipoRestaurante());
            restauranteResponseDTO.setCalificacion_promedio(restaurante.getCalificacion_promedio());
            Ubicacion ubicacion = restaurante.getUbicacion();
            restauranteResponseDTO.setUbicacion(new UbicacionResponseDTO(
                    ubicacion.getCiudad(),
                    ubicacion.getDireccionCompleta(),
                    ubicacion.getLongitud(),
                    ubicacion.getLatitud(),
                    ubicacion.getCodigoPostal()));
            newRestaurantes.add(restauranteResponseDTO);
        }

        return newRestaurantes;
    }


    public List<RestauranteResponseDTO> getNear(UbiRequestDTO requestDTO) throws IOException, InterruptedException, ApiException {
        Double lat = requestDTO.getLatitud();
        Double lng = requestDTO.getLongitud();
        return ubicacionService.getNearRestaurantUbication(lat,lng);
    }
}
