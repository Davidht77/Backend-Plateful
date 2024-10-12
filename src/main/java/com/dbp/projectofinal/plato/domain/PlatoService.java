package com.dbp.projectofinal.plato.domain;

import com.dbp.projectofinal.plato.infrastructure.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    public List<Plato> getAllPlatos() {
        return platoRepository.findAll();
    }

    public Optional<Plato> getPlatoById(Long id) {
        return platoRepository.findById(id);
    }

    public Plato savePlato(Plato plato) {
        return platoRepository.save(plato);
    }

    public void deletePlato(Long id) {
        platoRepository.deleteById(id);
    }

    public List<Plato> getPlatosByCartaId(Long cartaId) {
        return platoRepository.findByCartaId(cartaId);
    }

    public List<Plato> getPlatosDisponibles() {
        return platoRepository.findByDisponibilidadTrue();
    }
    public Plato updatePlato(Plato plato) {
        return platoRepository.save(plato);
    }
}
