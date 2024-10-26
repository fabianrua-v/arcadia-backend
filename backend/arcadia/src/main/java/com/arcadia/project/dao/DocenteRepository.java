package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Docente;

public interface DocenteRepository extends  JpaRepository<Docente, Integer> {

    
}
