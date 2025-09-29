package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.exception.UserNotFoundException;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = null;

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(User.Role role) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
        return users.stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getFullName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
    }
}
