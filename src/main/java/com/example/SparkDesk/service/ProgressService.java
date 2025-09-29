package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.progress.ProgressDTO;
import com.example.SparkDesk.dto.progress.CreateProgressDTO;

import java.util.List;

public interface ProgressService {
    ProgressDTO addProgress(CreateProgressDTO dto, Long taskId);
    List<ProgressDTO> getProgressByTask(Long taskId);
}