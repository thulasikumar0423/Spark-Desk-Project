package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.progress.ProgressDTO;
import com.example.SparkDesk.dto.progress.CreateProgressDTO;
import com.example.SparkDesk.exception.ProgressNotFoundException;
import com.example.SparkDesk.exception.TaskNotFoundException;
import com.example.SparkDesk.model.Progress;
import com.example.SparkDesk.model.Task;
import com.example.SparkDesk.repository.ProgressRepository;
import com.example.SparkDesk.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository = null;
    private final TaskRepository taskRepository = null;

    @Override
    public ProgressDTO addProgress(CreateProgressDTO dto, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Progress progress = new Progress();
        progress.setTask(task);
        progress.setDescription(dto.getDescription());
        progress.setProgressPercent(dto.getProgressPercent());

        progressRepository.save(progress);

        return mapToDTO(progress);
    }

    @Override
    public List<ProgressDTO> getProgressByTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));

        List<Progress> progresses = progressRepository.findByTask(task);
        if(progresses.isEmpty()) throw new ProgressNotFoundException("No progress found for this task");

        return progresses.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ProgressDTO mapToDTO(Progress progress) {
        return new ProgressDTO(
                progress.getId(),
                progress.getDescription(),
                progress.getProgressPercent(),
                progress.getCreatedAt()
        );
    }
}