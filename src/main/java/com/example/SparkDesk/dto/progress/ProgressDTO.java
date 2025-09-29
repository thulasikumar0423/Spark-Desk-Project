package com.example.SparkDesk.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class ProgressDTO {
    private Long id;
    private String description;
    private Integer progressPercent;
    private LocalDateTime createdAt;
}
