package com.dbp.projectofinal.ubicacion.domain;

import com.dbp.projectofinal.MapsAPI.domain.GoogleMapsService;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.dto.RestauranteResponseDTO;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import com.dbp.projectofinal.ubicacion.infrastructure.UbicacionRepository;
import com.google.maps.errors.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getUbicacionById(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion saveUbicacion(Ubicacion usuario) {
        return ubicacionRepository.save(usuario);
    }

    public void deleteUbicacion(Long id) {
        ubicacionRepository.deleteById(id);}

    public List<RestauranteResponseDTO> getNearRestaurantUbication(Double latitud, Double longitud) throws IOException, InterruptedException, ApiException {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        List<RestauranteResponseDTO> restaurantsNear = new ArrayList<>();

        //convertir km a lat y lng:
        //para 1km alredor
        double variacionLAT = 1000/111320.0;
        double variacionLNG = 1000/(111320.0*Math.cos(Math.toRadians(latitud)));

        Double newlatitud = latitud+variacionLAT; Double newlongitud = longitud+variacionLNG;

        for(Restaurante restaurante: restaurantes){
            Ubicacion ubicacion = restaurante.getUbicacion();
            if(ubicacion.getLatitud()<= newlatitud && ubicacion.getLongitud()<=newlatitud &&
            ubicacion.getLatitud() > latitud && ubicacion.getLongitud() > longitud){
                UbicacionResponseDTO ubicacionResponseDTO =  googleMapsService.getUbicationDetails(ubicacion.getDireccionCompleta());
                RestauranteResponseDTO restauranteResponseDTO = new RestauranteResponseDTO();
                restauranteResponseDTO.setNombre_restaurante(restaurante.getNombre_restaurante());
                restauranteResponseDTO.setTipoRestaurante(restaurante.getTipoRestaurante());
                restauranteResponseDTO.setHorario(restaurante.getHorario());
                restauranteResponseDTO.setCalificacion_promedio(restaurante.getCalificacion_promedio());
                restauranteResponseDTO.setUbicacion(ubicacionResponseDTO);
                restaurantsNear.add(restauranteResponseDTO);
            }
        }
        return restaurantsNear;
    }
    public Optional<Ubicacion> updateUbicacion(Long id, Ubicacion updatedUbicacion) {
        return ubicacionRepository.findById(id).map(existingUbicacion -> {
            existingUbicacion.setCiudad(updatedUbicacion.getCiudad());
            existingUbicacion.setDireccionCompleta(updatedUbicacion.getDireccionCompleta());
            existingUbicacion.setLatitud(updatedUbicacion.getLatitud());
            existingUbicacion.setLongitud(updatedUbicacion.getLongitud());

            return ubicacionRepository.save(existingUbicacion);
        });
    }

}
