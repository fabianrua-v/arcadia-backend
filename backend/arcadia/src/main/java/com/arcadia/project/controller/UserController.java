package com.arcadia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcadia.project.entity.User;
import com.arcadia.project.service.UserService;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);

    }

    @GetMapping({ "/forAdmin" })
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({ "/forUser" })
    @PreAuthorize("hasAnyRole('Admin','user')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/usersroles")
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAllUsersWithRoles() {
        return userService.getAllUsersWithRoles();
    }

    @PostMapping("/updateRole")
    @PreAuthorize("hasRole('Admin')")
    public User updateRole(@RequestBody UpdateRoleRequest request) {
        return userService.updateRole(request.getUsername(), request.getNewRoleName());
    }

    public static class UpdateRoleRequest {
        private String username;
        private String newRoleName;

        // getters y setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNewRoleName() {
            return newRoleName;
        }

        public void setNewRoleName(String newRoleName) {
            this.newRoleName = newRoleName;
        }
    }

}
