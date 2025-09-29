package com.example.SparkDesk.dto.progress;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProgressDTO {
    @NotBlank private String description;
    @NotNull @Min(0) @Max(100) private Integer progressPercent;
}
