package com.arcadia.project.service;

import com.arcadia.project.dao.CaracteristicaRepository;
import com.arcadia.project.dao.FactorRepository;
import com.arcadia.project.entity.Caracteristica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaService {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private FactorRepository factorRepository;

    public List<Caracteristica> getAllCaracteristicas() {
        return caracteristicaRepository.findAll();
    }

    public Optional<Caracteristica> getCaracteristicaById(Integer id) {
        return caracteristicaRepository.findById(id);
    }

    public Caracteristica createCaracteristica(Caracteristica caracteristica) {
        return caracteristicaRepository.save(caracteristica);
    }

    public Caracteristica updateCaracteristica(Integer id, Caracteristica caracteristicaDetails) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caracteristica no encontrada con id: " + id));

        caracteristica.setCodigo(caracteristicaDetails.getCodigo());
        caracteristica.setNombre(caracteristicaDetails.getNombre());
        caracteristica.setDescripcion(caracteristicaDetails.getDescripcion());
        caracteristica.setEstado(caracteristicaDetails.isEstado());
        caracteristica.setFechaModificacion(caracteristicaDetails.getFechaModificacion());

        return caracteristicaRepository.save(caracteristica);
    }

    public void deleteCaracteristica(Integer id) {
        caracteristicaRepository.deleteById(id);
    }
}
