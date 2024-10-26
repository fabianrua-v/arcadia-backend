package com.arcadia.project.controller;

import com.arcadia.project.entity.Caracteristica;
import com.arcadia.project.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @GetMapping("/caracteristica")
    public ResponseEntity<List<Caracteristica>> getAllCaracteristica() {
    List<Caracteristica> caracteristica = caracteristicaService.getAllCaracteristicas();
    return ResponseEntity.ok(caracteristica);
    }

    @GetMapping("/caracteristica/{id}")
    public ResponseEntity<Caracteristica> getCaracteristicaById(@PathVariable Integer id) {
        return caracteristicaService.getCaracteristicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/caracteristica")
    public Caracteristica createCaracteristica(@RequestBody Caracteristica caracteristica) {
        return caracteristicaService.createCaracteristica(caracteristica);
    }

    @PutMapping("/caracteristica/{id}")
    public ResponseEntity<Caracteristica> updateCaracteristica(@PathVariable Integer id, @RequestBody Caracteristica caracteristicaDetails) {
        try {
            Caracteristica updatedCaracteristica = caracteristicaService.updateCaracteristica(id, caracteristicaDetails);
            return ResponseEntity.ok(updatedCaracteristica);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/caracteristica/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Integer id) {
        caracteristicaService.deleteCaracteristica(id);
        return ResponseEntity.noContent().build();
    }
}

