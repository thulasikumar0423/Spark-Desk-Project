package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.Progress;
import com.example.SparkDesk.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByTask(Task task);
}
