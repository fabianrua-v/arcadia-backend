package com.arcadia.project.service;


import com.arcadia.project.dao.FactorRepository;
import com.arcadia.project.entity.Factor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactorService {

    @Autowired
    private FactorRepository factorRepository;

    public List<Factor> getAllFactors() {
        return factorRepository.findAll();
    }

    public Optional<Factor> getFactorById(Integer id) {
        return factorRepository.findById(id);
    }

    public Factor createFactor(Factor factor) {
        return factorRepository.save(factor);
    }

    public Factor updateFactor(Integer id, Factor factorDetails) {
        Factor factor = factorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factor no encontrado con id: " + id));

        factor.setCodigo(factorDetails.getCodigo());
        factor.setNombre(factorDetails.getNombre());
        factor.setEstado(factorDetails.isEstado());
        factor.setFechaModificacion(factorDetails.getFechaModificacion());
        factor.setModeloId(factorDetails.getModeloId());

        return factorRepository.save(factor);
    }

    public void deleteFactor(Integer id) {
        factorRepository.deleteById(id);
    }
}
    
