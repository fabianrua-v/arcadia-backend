package com.arcadia.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcadia.project.dao.FacultadRepository;
import com.arcadia.project.entity.Facultad;

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    public Facultad createFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    public List<Facultad> getAllFacultads() {
        return facultadRepository.findAll();
    }

    public Facultad getFacultadById(Integer id) {
        return facultadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Facultad no encontrada con id: " + id));
    }

    public Facultad updateFacultad(Integer id, Facultad updatedFacultad) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Facultad no encontrada con id: " + id));
    
        facultad.setNombre(updatedFacultad.getNombre());
        return facultadRepository.save(facultad);
    }

    public void deleteFacultad(Integer id) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Facultad no encontrada con id: " + id));
        
        facultadRepository.delete(facultad);
    }

}