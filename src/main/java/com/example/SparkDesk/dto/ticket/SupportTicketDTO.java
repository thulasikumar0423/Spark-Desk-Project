package com.example.SparkDesk.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class SupportTicketDTO {
    private Long id;
    private String title;
    private String description;
    private Long raisedById;
    private Long assignedToItId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
