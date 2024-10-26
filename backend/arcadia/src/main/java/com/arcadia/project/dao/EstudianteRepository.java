package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    
}