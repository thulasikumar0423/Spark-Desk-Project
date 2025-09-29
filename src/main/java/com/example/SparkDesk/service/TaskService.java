package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.dto.task.CreateTaskDTO;
import com.example.SparkDesk.dto.task.UpdateTaskStatusDTO;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskDTO createTask(CreateTaskDTO dto, Long creatorId);
    TaskDTO assignTask(Long taskId, Long assigneeId);
    TaskDTO updateTaskProgress(UpdateTaskStatusDTO dto);
    List<TaskDTO> getTasksAssignedToday(Long userId, LocalDate today);
    List<TaskDTO> getTasksAssignedByLead(Long leadId);
    List<TaskDTO> getAllTasks();
}
