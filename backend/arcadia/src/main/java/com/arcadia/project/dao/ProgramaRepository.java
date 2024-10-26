package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Programa;



public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    
}
