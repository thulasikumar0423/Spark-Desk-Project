package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.dto.auth.RegisterRequestDTO;
import com.example.SparkDesk.service.UserService;
import com.example.SparkDesk.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "API")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-manager")
    public ResponseEntity<UserResponseDTO> createManager(@Valid @RequestBody RegisterRequestDTO dto) {
        // ensure role is MANAGER for this endpoint
        dto.setRole("MANAGER");
        return ResponseEntity.ok(authService.register(dto));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(((com.example.SparkDesk.service.impl.UserServiceImpl)userService).getAllUsers());
    }
}
