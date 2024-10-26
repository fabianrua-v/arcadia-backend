package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcadia.project.entity.Estudiante;
import com.arcadia.project.service.EstudianteService;
import java.util.*;

public class EstudianteController {
     @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/estudiante")
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    @GetMapping("/estudiante")
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
    List<Estudiante> estudiante = estudianteService.getAllEstudiantes();
    return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<Estudiante> getDocenteById(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        return ResponseEntity.ok(estudiante);
    }

     @PutMapping("/estudiante/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Integer id, @RequestBody Estudiante updateEstudiante) {
        Estudiante estudiante = estudianteService.updateEstudiante(id, updateEstudiante);
        return ResponseEntity.ok(estudiante);
    }

    @DeleteMapping("/estudiante/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Integer id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build(); 
     }
    
}
