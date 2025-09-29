package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.model.User;

import java.util.List;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getUsersByRole(User.Role role);
}
