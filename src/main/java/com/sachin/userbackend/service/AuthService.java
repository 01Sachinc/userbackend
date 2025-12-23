package com.sachin.userbackend.service;

import org.springframework.stereotype.Service;

import com.sachin.userbackend.dto.LoginRequestDTO;
import com.sachin.userbackend.dto.LoginResponseDTO;
import com.sachin.userbackend.dto.UserRequestDTO;
import com.sachin.userbackend.dto.UserResponseDTO;
import com.sachin.userbackend.model.User;
import com.sachin.userbackend.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    public UserResponseDTO register(UserRequestDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // plain text for now

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail());
    }

    // LOGIN
    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO(
                "Login successful",
                user.getId(),
                user.getEmail());
    }
}
