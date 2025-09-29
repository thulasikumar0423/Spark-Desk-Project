package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.Task;
import com.example.SparkDesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToAndDeadline(User assignedTo, LocalDate deadline);
    List<Task> findByAssignedBy(User assignedBy);
}
