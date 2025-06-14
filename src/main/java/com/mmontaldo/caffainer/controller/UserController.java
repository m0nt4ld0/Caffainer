package com.mmontaldo.caffainer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmontaldo.caffainer.dto.user.UserDto;
import com.mmontaldo.caffainer.dto.user.UserWithPasswordDto;
import com.mmontaldo.caffainer.service.user.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile() {
        String usernameFromToken = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        return ResponseEntity.ok(userService.getUserByUsername(usernameFromToken));
    }


    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create") 
    public UserDto newUser(@RequestBody UserWithPasswordDto user) {
        return userService.createUser(user);
    }
}
