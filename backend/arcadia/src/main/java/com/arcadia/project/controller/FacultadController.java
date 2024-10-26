package com.arcadia.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcadia.project.entity.Facultad;
import com.arcadia.project.service.FacultadService;

@RestController
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @PostMapping("/facultad")
    public Facultad createFacultad(@RequestBody Facultad facultad) {
        return facultadService.createFacultad(facultad);
    }

    @GetMapping("/facultad")
    public ResponseEntity<List<Facultad>> getAllFacultads() {
    List<Facultad> facultad = facultadService.getAllFacultads();
    return ResponseEntity.ok(facultad);
    }

    @GetMapping("/facultad/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable Integer id) {
        Facultad facultad = facultadService.getFacultadById(id);
        return ResponseEntity.ok(facultad);
    }

     @PutMapping("/facultad/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Integer id, @RequestBody Facultad updatedFacultad) {
        Facultad facultad = facultadService.updateFacultad(id, updatedFacultad);
        return ResponseEntity.ok(facultad);
    }

    @DeleteMapping("/facultad/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Integer id) {
        facultadService.deleteFacultad(id);
        return ResponseEntity.noContent().build(); 
     }


}