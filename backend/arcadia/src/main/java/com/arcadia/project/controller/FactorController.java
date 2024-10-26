package com.arcadia.project.controller;

import com.arcadia.project.entity.Factor;
import com.arcadia.project.entity.Facultad;
import com.arcadia.project.service.FactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FactorController {

    @Autowired
    private FactorService factorService;

    @GetMapping("/factor")
    public ResponseEntity<List<Factor>> getAllFactor() {
    List<Factor> factor = factorService.getAllFactors();
    return ResponseEntity.ok(factor);
    }

    
    @GetMapping("/factor/{id}")
    public ResponseEntity<Factor> getFactorById(@PathVariable Integer id) {
        return factorService.getFactorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/factor")
    public Factor createFactor(@RequestBody Factor factor) {
        return factorService.createFactor(factor);
    }

    @PutMapping("/factor/{id}")
    public ResponseEntity<Factor> updateFactor(@PathVariable Integer id, @RequestBody Factor factorDetails) {
        try {
            Factor updatedFactor = factorService.updateFactor(id, factorDetails);
            return ResponseEntity.ok(updatedFactor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/factor/{id}")
    public ResponseEntity<Void> deleteFactor(@PathVariable Integer id) {
        factorService.deleteFactor(id);
        return ResponseEntity.noContent().build();
    }
}
