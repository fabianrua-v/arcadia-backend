package com.arcadia.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcadia.project.dao.RoleDao;
import com.arcadia.project.dao.UserDao;
import com.arcadia.project.entity.User;
import com.arcadia.project.entity.Role;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "your_secret_key"; // Cambia esto por tu clave secreta
    private static final long RESET_TOKEN_VALIDITY = 3600; // 1 hora en segundos

    public User registerNewUser(User user) {
        Role role = roleDao.findById("user").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        // Envía un correo electrónico de verificación
        String token = UUID.randomUUID().toString();
        emailService.sendVerificationEmail(user.getUsername(), token);

        // Guarda el token de verificación en la base de datos
        user.setVerificationToken(token);

        return userDao.save(user);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public User findByVerificationToken(String token) {
        return userDao.findByVerificationToken(token);
    }

    public User updateRole(String username, String newRoleName) {
        User user = userDao.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Role newRole = roleDao.findById(newRoleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + newRoleName));
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(newRole);
        user.setRole(newRoles);
        return userDao.save(user);
    }

    public User updateUser(User updatedUser) {
        User existingUser = userDao.findById(updatedUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + updatedUser.getUsername()));

        // Actualiza los campos
        existingUser.setUserFirstName(updatedUser.getUserFirstName());
        existingUser.setUserLastName(updatedUser.getUserLastName());

        // Si se incluye una nueva contraseña, actualiza la contraseña también
        if (updatedUser.getUserPassword() != null && !updatedUser.getUserPassword().isEmpty()) {
            existingUser.setUserPassword(getEncodedPassword(updatedUser.getUserPassword()));
        }

        return userDao.save(existingUser);
    }

    public List<User> getAllUsersWithRoles() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record role");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUsername("admin123");
        adminUser.setUserPassword(getEncodedPassword("12345"));
        adminUser.setVerified(true);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Método para generar un token de recuperación
    public String generateResetToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + RESET_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Método para enviar un correo de recuperación
    public void sendResetEmail(String email) {
        User user = userDao.findByUsername(email); // Asegúrate de tener este método en UserDao
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String token = generateResetToken(user);
        String resetUrl = "http://localhost:8090/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperación de Contraseña");
        message.setText("Haga clic en el siguiente enlace para recuperar su contraseña: " + resetUrl);
        emailService.sendEmail(message); // Asegúrate de tener un método para enviar correos
    }

    // Método para restablecer la contraseña
    public void resetPassword(String token, String newPassword) {
        Claims claims = validateResetToken(token);
        String username = claims.getSubject();
    
        User user = userDao.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        user.setUserPassword(getEncodedPassword(newPassword));
        userDao.save(user);
    }
    

    // Método para validar el token de recuperación
    private Claims validateResetToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
