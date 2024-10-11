package com.dbp.projectofinal.propietario.domain;

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

    public Propietario savePropietario(Propietario usuario) {
        return propietarioRepository.save(usuario);
    }

    public void deletePropietario(Long id) {
        propietarioRepository.deleteById(id);
    }
}
