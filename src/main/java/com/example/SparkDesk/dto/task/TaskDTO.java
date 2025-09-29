package com.example.SparkDesk.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Long assignedToId;
    private Long assignedById;
    private String status;
    private String priority;
    private int progress;
    private LocalDate deadline;
}
