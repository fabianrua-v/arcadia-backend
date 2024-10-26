package com.arcadia.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arcadia.project.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String>{
    
}
