package com.dbp.projectofinal.resena.domain;

import com.dbp.projectofinal.exceptions.RestauranteNotFoundException;
import com.dbp.projectofinal.exceptions.UsuarioNotFoundException;
import com.dbp.projectofinal.resena.dto.CreateResenaDTO;
import com.dbp.projectofinal.resena.infrastructure.ResenaRepository;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.restaurante.infrastructure.RestauranteRepository;
import com.dbp.projectofinal.usuario.domain.Usuario;
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

    public Resena saveResena(CreateResenaDTO createResenaDTO) {
        Resena resena = new Resena();
        resena.setCalificacion(createResenaDTO.getCalificacion());
        resena.setContenido(createResenaDTO.getContenido());

        Optional<Usuario> usuario = usuarioRepository.findById(createResenaDTO.getId_usuario());
        if(usuario.isEmpty())
            throw new UsuarioNotFoundException("");
        resena.setUsuario(usuario.get());

        Optional<Restaurante> restaurante = restauranteRepository.findById(createResenaDTO.getId_restaurante());
        if(restaurante.isEmpty())
            throw new RestauranteNotFoundException("");
        resena.setRestaurante(restaurante.get());

        restaurante.get().actualizarCalificacion();
        restauranteRepository.save(restaurante.get());
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
            existingResena.setContenido(updatedResena.getContenido());
            existingResena.setUsuario(updatedResena.getUsuario());
            existingResena.setRestaurante(updatedResena.getRestaurante());
            existingResena.setComentarios(updatedResena.getComentarios());
            return resenaRepository.save(existingResena);
        } else {
            throw new RuntimeException("Resena no encontrada");
        }
    }


    public List<Resena> getResenasByUsuario(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty())
            throw new UsuarioNotFoundException("");
        return usuario.get().getResenas();
    }

    public List<Resena> getResenasByRestaurante(Long idRestaurante) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(idRestaurante);
        if(restaurante.isEmpty())
            throw new RestauranteNotFoundException("");
        return restaurante.get().getResenas();
    }

    public double getCalificacionPromedioRestaurante(Long idRestaurante) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(idRestaurante);
        if(restaurante.isEmpty())
            throw new RestauranteNotFoundException("");
        List<Resena> resenas = restaurante.get().getResenas();
        return resenas.stream()
                .mapToDouble(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    }
}
