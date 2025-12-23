package com.sachin.userbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sachin.userbackend.model.User;
import com.sachin.userbackend.service.UserService;

import jakarta.validation.Valid;
import com.sachin.userbackend.dto.UserRequestDTO;
import com.sachin.userbackend.dto.UserResponseDTO;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(userService.createUser(dto));
    }

    // READ ALL
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {

        UserResponseDTO updated = userService.updateUser(id, dto);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}