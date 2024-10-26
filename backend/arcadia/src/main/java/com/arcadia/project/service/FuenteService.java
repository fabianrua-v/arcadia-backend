package com.arcadia.project.service;

import com.arcadia.project.dao.FuenteRepository;
import com.arcadia.project.entity.Fuente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuenteService {

    @Autowired
    private FuenteRepository fuenteRepository;

    public List<Fuente> getAllFuentes() {
        return fuenteRepository.findAll();
    }

    public Optional<Fuente> getFuenteById(Long id) {
        return fuenteRepository.findById(id);
    }

    public Fuente createFuente(Fuente fuente) {
        return fuenteRepository.save(fuente);
    }

    public Fuente updateFuente(Long id, Fuente fuenteDetalles) {
        Fuente fuenteExistente = fuenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuente no encontrada con ID: " + id));
        
        fuenteExistente.setNombre(fuenteDetalles.getNombre());
        fuenteExistente.setSoporte(fuenteDetalles.getSoporte());

        return fuenteRepository.save(fuenteExistente);
    }

    public void deleteFuente(Long id) {
        fuenteRepository.deleteById(id);
    }
}
