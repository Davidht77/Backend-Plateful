package com.dbp.projectofinal.propietario.domain;

import com.dbp.projectofinal.auth.dto.RegisterReq;
import com.dbp.projectofinal.propietario.dto.CreatePropietarioDTO;
import com.dbp.projectofinal.propietario.infrastructure.PropietarioRepository;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<Propietario> getAllPropietarios() {
        return propietarioRepository.findAll();
    }

    public Optional<Propietario> getPropietarioById(Long id) {
        return propietarioRepository.findById(id);
    }

    public void savePropReq(Usuario registerReq){
        Propietario propietario = new Propietario();
        propietario.setNombre(registerReq.getNombre());
        propietario.setEmail(registerReq.getEmail());
        propietario.setPassword(registerReq.getPassword());
        propietario.setTelefono(registerReq.getTelefono());
        propietario.setFechaNacimiento(registerReq.getFechaNacimiento());
        propietarioRepository.save(propietario);
    }

    public Propietario savePropietarioDTO(CreatePropietarioDTO createPropietarioDTO) {
        Propietario propietario = new Propietario();
        propietario.setNombre(createPropietarioDTO.getNombre());
        propietario.setEmail(createPropietarioDTO.getCorreo());
        propietario.setTipoDocumento(createPropietarioDTO.getTipoDocumento());
        propietario.setNumeroDocumento(createPropietarioDTO.getNumeroDocumento());
        propietario.setFotoPerfil(createPropietarioDTO.getFotoPerfil());
        return propietarioRepository.save(propietario);
    }

    public Propietario savePropietario(Propietario propietario){
        return propietarioRepository.save(propietario);
    }

    public void deletePropietario(Long id) {
        propietarioRepository.deleteById(id);
    }
}
