package com.dbp.projectofinal.comentario.domain;

import com.dbp.projectofinal.comentario.dto.CreateComentarioDTO;
import com.dbp.projectofinal.comentario.dto.ComentarioDTO;
import com.dbp.projectofinal.comentario.domain.Comentario;
import com.dbp.projectofinal.comentario.infrastructure.ComentarioRepository;
import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<ComentarioDTO> getAllComentarios() {
        return comentarioRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO saveComentario(CreateComentarioDTO createComentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setContenido(createComentarioDTO.getContenido());
        comentario.setResena(new Resena(createComentarioDTO.getId_resena())); // Usando el constructor de ID
        comentario.setUsuario(new Usuario(createComentarioDTO.getId_usuario())); // Usando el constructor de ID

        Comentario savedComentario = comentarioRepository.save(comentario);
        return convertToDTO(savedComentario);
    }

    public ComentarioDTO getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return convertToDTO(comentario);
    }

    public List<ComentarioDTO> getComentariosByResenaId(Long resenaId) {
        return comentarioRepository.findByResenaId(resenaId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> getComentariosByUsuarioId(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO updateComentario(Long id, CreateComentarioDTO createComentarioDTO) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        comentario.setContenido(createComentarioDTO.getContenido());
        comentario.setResena(new Resena(createComentarioDTO.getId_resena()));
        comentario.setUsuario(new Usuario(createComentarioDTO.getId_usuario()));
        Comentario updatedComentario = comentarioRepository.save(comentario);
        return convertToDTO(updatedComentario);
    }

    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId_comentario(comentario.getId_comentario());
        dto.setContenido(comentario.getContenido());
        dto.setId_resena(comentario.getResena().getId_resena());
        dto.setId_usuario(comentario.getUsuario().getId_usuario());
        dto.setFecha(comentario.getFecha());
        return dto;
    }
}

