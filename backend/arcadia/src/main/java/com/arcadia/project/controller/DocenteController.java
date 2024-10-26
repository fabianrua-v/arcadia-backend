package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arcadia.project.entity.Docente;
import com.arcadia.project.service.DocenteService;
import java.util.List;;

public class DocenteController {
    @Autowired
    private DocenteService docenteService;

    @PostMapping("/docente")
    public Docente createDocente(@RequestBody Docente docente) {
        return docenteService.createDocente(docente);
    }

    @GetMapping("/docente")
    public ResponseEntity<List<Docente>> getAllDocentes() {
    List<Docente> docente = docenteService.getAllDocentes();
    return ResponseEntity.ok(docente);
    }

    @GetMapping("/docente/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Integer id) {
        Docente docente = docenteService.getDocenteById(id);
        return ResponseEntity.ok(docente);
    }

     @PutMapping("/docente/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Integer id, @RequestBody Docente updatedDocente) {
        Docente docente = docenteService.updateDocente(id, updatedDocente);
        return ResponseEntity.ok(docente);
    }

    @DeleteMapping("/docente/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Integer id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build(); 
     }


}
