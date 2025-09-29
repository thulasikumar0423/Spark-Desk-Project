package com.example.SparkDesk.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
public class CreateTaskDTO {
    @NotBlank private String title;
    private String description;
    @NotNull private Long assigneeId;
    @NotBlank private String priority;
    @NotNull private LocalDate deadline;
}
