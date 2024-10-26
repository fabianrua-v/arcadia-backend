package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Caracteristica;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer>{
    
}
