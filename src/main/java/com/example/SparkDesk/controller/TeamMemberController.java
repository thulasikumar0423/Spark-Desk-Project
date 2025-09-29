package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.dto.progress.ProgressDTO;
import com.example.SparkDesk.service.TaskService;
import com.example.SparkDesk.service.ProgressService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@Tag(name = "API")
@RequestMapping("/api/team-member")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TaskService taskService;
    private final ProgressService progressService;

    @PreAuthorize("hasRole('TEAM_MEMBER')")
    @GetMapping("/tasks/today")
    public ResponseEntity<List<TaskDTO>> getTasksAssignedToday(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getTasksAssignedToday(userId));
    }

    @PreAuthorize("hasRole('TEAM_MEMBER')")
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getMyTasks(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PreAuthorize("hasRole('TEAM_MEMBER')")
    @GetMapping("/tasks/{taskId}/progress")
    public ResponseEntity<List<ProgressDTO>> getProgress(@PathVariable Long taskId) {
        return ResponseEntity.ok(progressService.getProgressByTask(taskId));
    }
}
