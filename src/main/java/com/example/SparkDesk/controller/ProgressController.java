package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.progress.CreateProgressDTO;
import com.example.SparkDesk.dto.progress.ProgressDTO;
import com.example.SparkDesk.service.ProgressService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/task/{taskId}")
    public ResponseEntity<ProgressDTO> addProgress(@PathVariable Long taskId, @Valid @RequestBody CreateProgressDTO dto) {
        ProgressDTO p = progressService.addProgress(dto, taskId);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ProgressDTO>> getProgress(@PathVariable Long taskId) {
        return ResponseEntity.ok(progressService.getProgressByTask(taskId));
    }
}
