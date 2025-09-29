package com.example.SparkDesk.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(columnDefinition = "TEXT")
    private String participants; // Could be JSON string or separate table

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    // Getters and setters
}
