package com.dbp.projectofinal.comentario.domain;

import com.dbp.projectofinal.comentario.infrastructure.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario saveComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }
}
