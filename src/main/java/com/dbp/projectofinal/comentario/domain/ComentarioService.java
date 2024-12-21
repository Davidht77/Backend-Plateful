package com.dbp.projectofinal.comentario.domain;

import com.dbp.projectofinal.comentario.dto.CreateComentarioDTO;
import com.dbp.projectofinal.comentario.dto.ComentarioDTO;
import com.dbp.projectofinal.comentario.domain.Comentario;
import com.dbp.projectofinal.comentario.infrastructure.ComentarioRepository;
import com.dbp.projectofinal.exceptions.ResenaNotFoundException;
import com.dbp.projectofinal.exceptions.UsuarioNotFoundException;
import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.resena.infrastructure.ResenaRepository;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ComentarioDTO> getAllComentarios() {
        return comentarioRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO saveComentario(CreateComentarioDTO createComentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setContenido(createComentarioDTO.getContenido());
        Optional<Resena> resena = resenaRepository.findById(createComentarioDTO.getId_resena());
        if(resena.isEmpty()){
            throw new ResenaNotFoundException("");
        }
        comentario.setResena(resena.get());
        Optional<Usuario> usuario = usuarioRepository.findById(createComentarioDTO.getId_usuario());
        if(usuario.isEmpty()){
            throw new ResenaNotFoundException("");
        }
        comentario.setUsuario(usuario.get());

        Comentario savedComentario = comentarioRepository.save(comentario);
        return convertToDTO(savedComentario);
    }

    public ComentarioDTO getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return convertToDTO(comentario);
    }

    public List<ComentarioDTO> getComentariosByResenaId(Long resenaId) {
        Optional<Resena> resena = resenaRepository.findById(resenaId);
        if (resena.isEmpty())
            throw new ResenaNotFoundException("");
        return  resena.get().getComentarios()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> getComentariosByUsuarioId(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty())
            throw new UsuarioNotFoundException("");
        return comentarioRepository.findByUsuario(usuario.get())
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
        dto.setNombre_usuario(comentario.getUsuario().getNombre());
        dto.setFecha(comentario.getFecha());
        return dto;
    }
}

