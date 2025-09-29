package com.example.SparkDesk.dto.task;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateTaskStatusDTO {
    @NotNull private Long id;
    @NotBlank private String status;
    @Min(0) @Max(100) private int progress;
}
