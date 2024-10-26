package com.arcadia.project.controller;

import com.arcadia.project.entity.Escala;
import com.arcadia.project.service.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EscalaController {

    @Autowired
    private EscalaService escalaService;

    @GetMapping("/escala")
    public List<Escala> getAllEscalas() {
        return escalaService.getAllEscalas();
    }

    @GetMapping("/escala/{id}")
    public ResponseEntity<Escala> getEscalaById(@PathVariable Long id) {
        Optional<Escala> escala = escalaService.getEscalaById(id);
        return escala.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/escala")
    public ResponseEntity<Escala> createEscala(@RequestBody Escala escala) {
        try {
            Escala newEscala = escalaService.saveEscala(escala);
            return ResponseEntity.ok(newEscala);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/escala/{id}")
    public ResponseEntity<Escala> updateEscala(@PathVariable Long id, @RequestBody Escala escalaDetails) {
        try {
            Escala updatedEscala = escalaService.updateEscala(id, escalaDetails);
            return ResponseEntity.ok(updatedEscala);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/escala/{id}")
    public ResponseEntity<Void> deleteEscala(@PathVariable Long id) {
        escalaService.deleteEscala(id);
        return ResponseEntity.noContent().build();
    }
}
