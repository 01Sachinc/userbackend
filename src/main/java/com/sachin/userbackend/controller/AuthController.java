package com.sachin.userbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sachin.userbackend.dto.LoginRequestDTO;
import com.sachin.userbackend.dto.LoginResponseDTO;
import com.sachin.userbackend.dto.UserRequestDTO;
import com.sachin.userbackend.dto.UserResponseDTO;
import com.sachin.userbackend.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(authService.register(dto));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }
}
