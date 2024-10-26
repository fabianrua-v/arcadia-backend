package com.arcadia.project.service;

import com.arcadia.project.dao.EscalaRepository;
import com.arcadia.project.entity.Escala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscalaService {

    @Autowired
    private EscalaRepository escalaRepository;

    public List<Escala> getAllEscalas() {
        return escalaRepository.findAll();
    }

    public Optional<Escala> getEscalaById(Long id) {
        return escalaRepository.findById(id);
    }

    public Escala saveEscala(Escala escala) {
        // Aquí podrías agregar validaciones adicionales, si es necesario
        return escalaRepository.save(escala);
    }

    public Escala updateEscala(Long id, Escala escalaDetails) {
        // Aquí debes verificar si la escala existe antes de actualizar
        if (!escalaRepository.existsById(id)) {
            throw new IllegalArgumentException("Escala no encontrada con ID: " + id);
        }
        escalaDetails.setEscalaId(id);  // Actualizar el ID para la entidad
        return escalaRepository.save(escalaDetails);
    }

    public void deleteEscala(Long id) {
        // Verificar si la escala existe antes de eliminar
        if (!escalaRepository.existsById(id)) {
            throw new IllegalArgumentException("Escala no encontrada con ID: " + id);
        }
        escalaRepository.deleteById(id);
    }
}
