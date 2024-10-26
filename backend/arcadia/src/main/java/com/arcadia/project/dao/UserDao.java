package com.arcadia.project.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arcadia.project.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String>{

       Optional<User> findById(String username);

       User findByVerificationToken(String token);
       
       User findByUsername(String username);


}
