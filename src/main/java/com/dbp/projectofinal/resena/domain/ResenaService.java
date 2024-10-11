package com.dbp.projectofinal.resena.domain;

import com.dbp.projectofinal.resena.infrastructure.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

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
}
