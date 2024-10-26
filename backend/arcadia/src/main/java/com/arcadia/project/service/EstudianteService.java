package com.arcadia.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.arcadia.project.dao.EstudianteRepository;
import com.arcadia.project.entity.Estudiante;

import java.util.List;

public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteById(Integer id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con id: " + id));
    }

    public Estudiante updateEstudiante(Integer id, Estudiante updatedEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrada con id: " + id));
    
        estudiante.setNombre(updatedEstudiante.getNombre());
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrada con id: " + id));
        
        estudianteRepository.delete(estudiante);
    }
    
}
