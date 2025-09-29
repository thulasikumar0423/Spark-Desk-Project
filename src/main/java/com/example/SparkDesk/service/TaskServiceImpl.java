package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.dto.task.CreateTaskDTO;
import com.example.SparkDesk.dto.task.UpdateTaskStatusDTO;
import com.example.SparkDesk.exception.TaskNotFoundException;
import com.example.SparkDesk.model.Task;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.TaskRepository;
import com.example.SparkDesk.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository = null;
    private final UserRepository userRepository = null;

    @Override
    public TaskDTO createTask(CreateTaskDTO dto, Long creatorId) {
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new TaskNotFoundException("Creator not found"));
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setDeadline(dto.getDeadline());
        task.setStatus(Task.Status.TODO);
        task.setAssignedBy(creator);

        taskRepository.save(task);
        return mapToDTO(task);
    }

    @Override
    public TaskDTO assignTask(Long taskId, Long assigneeId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new TaskNotFoundException("Assignee not found"));
        task.setAssignedTo(assignee);
        task.setStatus(Task.Status.IN_PROGRESS);
        taskRepository.save(task);
        return mapToDTO(task);
    }

    @Override
    public TaskDTO updateTaskProgress(UpdateTaskStatusDTO dto) {
        Task task = taskRepository.findById(dto.getId()).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setStatus(dto.getStatus());
        task.setProgress(dto.getProgress());
        taskRepository.save(task);
        return mapToDTO(task);
    }

    @Override
    public List<TaskDTO> getTasksAssignedToday(Long userId, LocalDate today) {
        User user = userRepository.findById(userId).orElseThrow(() -> new TaskNotFoundException("User not found"));
        List<Task> tasks = taskRepository.findByAssignedToAndDeadline(user, today);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksAssignedByLead(Long leadId) {
        User lead = userRepository.findById(leadId).orElseThrow(() -> new TaskNotFoundException("Team lead not found"));
        List<Task> tasks = taskRepository.findByAssignedBy(lead);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TaskDTO mapToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedTo() != null ? task.getAssignedTo().getId() : null,
                task.getAssignedBy() != null ? task.getAssignedBy().getId() : null,
                task.getStatus(),
                task.getPriority(),
                task.getProgress(),
                task.getDeadline()
        );
    }
}
