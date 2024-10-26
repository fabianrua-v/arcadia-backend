package com.arcadia.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.arcadia.project.dao.DocenteRepository;
import com.arcadia.project.entity.Docente;
import java.util.List;

public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    public Docente createDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }

    public Docente getDocenteById(Integer id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado con id: " + id));
    }

    public Docente updateDocente(Integer id, Docente updatedDocente) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrada con id: " + id));
    
        docente.setNombre(updatedDocente.getNombre());
        return docenteRepository.save(docente);
    }

    public void deleteDocente(Integer id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrada con id: " + id));
        
                docenteRepository.delete(docente);
    }
    
}
