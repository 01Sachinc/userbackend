package com.sachin.userbackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sachin.userbackend.model.User;
import com.sachin.userbackend.repository.UserRepository;

import com.sachin.userbackend.dto.UserRequestDTO;
import com.sachin.userbackend.dto.UserResponseDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail());
    }

    // READ ALL
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail()))
                .collect(Collectors.toList());
    }

    // UPDATE
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User updated = userRepository.save(user);

        return new UserResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail());
    }

    // DELETE
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
