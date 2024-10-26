package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcadia.project.entity.User;
import com.arcadia.project.service.UserService;

@RestController
public class VerificationController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token) {
        User user = userService.findByVerificationToken(token);
        if (user != null) {
            user.setVerified(true);
            userService.saveUser (user);
            return "Cuenta verificada con éxito";
        } else {
            return "Token de verificación inválido";
        }
    }
}