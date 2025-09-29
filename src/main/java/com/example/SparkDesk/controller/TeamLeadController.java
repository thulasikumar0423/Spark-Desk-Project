package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.task.CreateTaskDTO;
import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.service.TaskService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@Tag(name = "API")
@RequestMapping("/api/team-lead")
@RequiredArgsConstructor
public class TeamLeadController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('TEAM_LEAD')")
    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateTaskDTO dto) {
        Long creatorId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(taskService.createTask(dto, creatorId));
    }

    @PostMapping("/tasks/{id}/assign")
    public ResponseEntity<TaskDTO> assignTask(@PathVariable Long id, @RequestParam Long assigneeId) {
        return ResponseEntity.ok(taskService.assignTask(id, assigneeId));
    }

    @GetMapping("/tasks/assigned-by-me")
    public ResponseEntity<List<TaskDTO>> getAssignedByMe(@AuthenticationPrincipal UserDetails userDetails) {
        Long leadId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getAllTasks().stream().filter(t -> t.getAssignedById()!=null && t.getAssignedById().equals(leadId)).toList());
    }
}
