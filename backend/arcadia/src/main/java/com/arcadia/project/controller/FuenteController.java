package com.arcadia.project.controller;

import com.arcadia.project.entity.Fuente;
import com.arcadia.project.service.FuenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FuenteController {

    @Autowired
    private FuenteService fuenteService;

    @GetMapping("/fuente")
    public List<Fuente> getAllFuentes() {
        return fuenteService.getAllFuentes();
    }

    @GetMapping("/fuente/{id}")
    public ResponseEntity<Fuente> getFuenteById(@PathVariable Long id) {
        Optional<Fuente> fuente = fuenteService.getFuenteById(id);
        return fuente.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/fuente")
    public ResponseEntity<Fuente> createFuente(@RequestBody Fuente fuente) {
        Fuente nuevaFuente = fuenteService.createFuente(fuente);
        return ResponseEntity.ok(nuevaFuente);
    }

    @PutMapping("/fuente/{id}")
    public ResponseEntity<Fuente> updateFuente(@PathVariable Long id, @RequestBody Fuente fuenteDetalles) {
        Fuente fuenteActualizada = fuenteService.updateFuente(id, fuenteDetalles);
        return ResponseEntity.ok(fuenteActualizada);
    }

    @DeleteMapping("/fuente/{id}")
    public ResponseEntity<Void> deleteFuente(@PathVariable Long id) {
        fuenteService.deleteFuente(id);
        return ResponseEntity.noContent().build();
    }
}
