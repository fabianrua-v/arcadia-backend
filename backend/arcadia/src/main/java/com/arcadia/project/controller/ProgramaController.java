package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcadia.project.entity.Programa;
import com.arcadia.project.service.ProgramaService;

import java.util.List;

@RestController
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @PostMapping("/programa")
    public Programa createPrograma(@RequestBody Programa programa) {
        return programaService.createPrograma(programa);
    }

    @GetMapping("/programa")
    public ResponseEntity<List<Programa>> getAllPrograms() {
    List<Programa> programas = programaService.getAllPrograms();
    return ResponseEntity.ok(programas);
    }

    @GetMapping("/programa/{id}")
    public ResponseEntity<Programa> getProgramaById(@PathVariable Integer id) {
        Programa programa = programaService.getProgramaById(id);
        return ResponseEntity.ok(programa);
    }

    @PutMapping("/programa/{id}")
    public ResponseEntity<Programa> updatePrograma(@PathVariable Integer id, @RequestBody Programa updatedPrograma) {
        Programa programa = programaService.updatePrograma(id, updatedPrograma);
        return ResponseEntity.ok(programa);
    }

    @DeleteMapping("/programa/{id}")
    public ResponseEntity<Void> deletePrograma(@PathVariable Integer id) {
        programaService.deletePrograma(id);
        return ResponseEntity.noContent().build(); // Responde con 204 No Content si la eliminación fue exitosa
    } 
}