package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.auth.RegisterRequestDTO;
import com.example.SparkDesk.dto.auth.LoginRequestDTO;
import com.example.SparkDesk.dto.auth.PasswordResetDTO;
import com.example.SparkDesk.dto.user.UserResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO dto);
    void login(LoginRequestDTO dto, HttpServletRequest request);
    void resetPassword(PasswordResetDTO dto);
}
