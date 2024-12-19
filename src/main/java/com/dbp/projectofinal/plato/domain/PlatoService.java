package com.dbp.projectofinal.plato.domain;

import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.carta.infrastructure.CartaRepository;
import com.dbp.projectofinal.exceptions.CartaNotFoundException;
import com.dbp.projectofinal.exceptions.ResenaNotFoundException;
import com.dbp.projectofinal.plato.dto.CreatePlatoDTO;
import com.dbp.projectofinal.plato.infrastructure.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private CartaRepository cartaRepository;

    public List<Plato> getAllPlatos() {
        return platoRepository.findAll();
    }

    public Optional<Plato> getPlatoById(Long id) {
        return platoRepository.findById(id);
    }

    public Plato savePlato(CreatePlatoDTO createPlatoDTO) {
        Plato plato = new Plato();
        plato.setNombre(createPlatoDTO.getNombre());
        plato.setPrecio(createPlatoDTO.getPrecio());
        plato.setDisponibilidad(createPlatoDTO.getDisponibilidad());
        Optional<Carta> carta = cartaRepository.findById(createPlatoDTO.getId_carta());
        if(carta.isEmpty()){
            throw new ResenaNotFoundException("");
        }
        plato.setCarta(carta.get());
        return platoRepository.save(plato);
    }

    public void deletePlato(Long id) {
        platoRepository.deleteById(id);
    }

    public List<Plato> getPlatosByCartaId(Long cartaId) {
        Optional<Carta> carta = cartaRepository.findById(cartaId);
        if(carta.isEmpty())
            throw new CartaNotFoundException(cartaId);
        return carta.get().getPlatos();
    }

    public List<Plato> getPlatosDisponibles() {
        return platoRepository.findByDisponibilidadTrue();
    }
    public Plato updatePlato(Plato plato) {
        return platoRepository.save(plato);
    }
}
