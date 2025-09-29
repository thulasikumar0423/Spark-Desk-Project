package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.service.TaskService;
import com.example.SparkDesk.service.UserService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "API")
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final TaskService taskService;
    private final UserService userService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(((com.example.SparkDesk.service.impl.UserServiceImpl)userService).getAllUsers());
    }
}
