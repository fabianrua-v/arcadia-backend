package com.arcadia.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcadia.project.entity.Factor;

public interface FactorRepository extends JpaRepository<Factor, Integer> {
    
}
