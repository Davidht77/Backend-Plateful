package com.dbp.projectofinal.resena.domain;

import com.dbp.projectofinal.resena.infrastructure.ResenaRepository;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Resena> getAllResenas() {
        return resenaRepository.findAll();
    }

    public Optional<Resena> getResenaById(Long id) {
        return resenaRepository.findById(id);
    }

    public Resena saveResena(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void deleteResena(Long id) {
        resenaRepository.deleteById(id);
    }

    public Resena updateResena(Long id, Resena updatedResena) {
        Optional<Resena> existingResenaOpt = resenaRepository.findById(id);

        if (existingResenaOpt.isPresent()) {
            Resena existingResena = existingResenaOpt.get();
            existingResena.setCalificacion(updatedResena.getCalificacion());
            existingResena.setComentario(updatedResena.getComentario());
            existingResena.setUsuario(updatedResena.getUsuario());
            existingResena.setRestaurante(updatedResena.getRestaurante());
            return resenaRepository.save(existingResena);
        } else {
            throw new RuntimeException("Resena no encontrada");
        }
    }


    public List<Resena> getResenasByUsuario(Long idUsuario) {
        return resenaRepository.findByUsuarioId(idUsuario);
    }

    public List<Resena> getResenasByRestaurante(Long idRestaurante) {
        return resenaRepository.findByRestauranteId(idRestaurante);
    }

    public double getCalificacionPromedioRestaurante(Long idRestaurante) {
        List<Resena> resenas = resenaRepository.findByRestauranteId(idRestaurante);
        return resenas.stream()
                .mapToDouble(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    }
}
