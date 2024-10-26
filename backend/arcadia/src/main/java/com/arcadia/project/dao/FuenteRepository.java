package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Fuente;

public interface FuenteRepository extends JpaRepository <Fuente, Long> {
    
}
