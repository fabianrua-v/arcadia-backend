package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arcadia.project.entity.PasswordResetRequest;
import com.arcadia.project.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class passwordResetController {

    @Autowired
    private UserService userService;

    // Endpoint para solicitar un correo de recuperación
    @PostMapping("/reset-password/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        userService.sendResetEmail(email);
        return ResponseEntity.ok("Correo de recuperación enviado.");
    }

    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        System.out.println("Token recibido: " + request.getToken());
        System.out.println("Nueva contraseña: " + request.getNewPassword());
    
        try {
            userService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Contraseña restablecida con éxito.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    

}
