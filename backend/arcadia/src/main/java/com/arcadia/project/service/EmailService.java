package com.arcadia.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String token) {
        String subject = "Verifica tu cuenta";
        String verificationLink = "http://localhost:8090/verify?token=" + token;
        String body = "Haz clic en el siguiente enlace para verificar tu cuenta: " + verificationLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendResetEmail(String email, String token) {
        String subject = "Recuperación de Contraseña";
        String resetLink = "http://localhost:8090/reset-password?token=" + token;
        String body = "Haga clic en el siguiente enlace para recuperar su contraseña: " + resetLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }
}
