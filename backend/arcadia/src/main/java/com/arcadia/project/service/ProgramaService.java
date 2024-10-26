package com.arcadia.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arcadia.project.entity.Programa;
import java.util.List;
import com.arcadia.project.dao.FacultadRepository;
import com.arcadia.project.dao.ProgramaRepository;
import com.arcadia.project.entity.Facultad;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;
    
    @Autowired
    private FacultadRepository facultadRepository;

    public Programa createPrograma(Programa programa) {
        // Obtener la facultad completa de la base de datos
        Facultad facultad = facultadRepository.findById(programa.getFacultad().getFacultadId())
                .orElseThrow(() -> new IllegalArgumentException("Facultad no encontrada con id: " + programa.getFacultad().getFacultadId()));
        
        // Asignar la facultad completa al programa
        programa.setFacultad(facultad);

        // Guardar el programa
        return programaRepository.save(programa);
    }

    public List<Programa> getAllPrograms() {
        return programaRepository.findAll();
    }

    public Programa getProgramaById(Integer id) {
        return programaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado con id: " + id));
    }

    public Programa updatePrograma(Integer id, Programa updatedPrograma) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado con id: " + id));
    
        programa.setNombre(updatedPrograma.getNombre()); // Actualiza los atributos que quieras
        programa.setFacultad(updatedPrograma.getFacultad()); // Si también quieres actualizar la facultad
        return programaRepository.save(programa);
    }
    
    public void deletePrograma(Integer id) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programa no encontrado con id: " + id));
        
        programaRepository.delete(programa);
    }
    
}