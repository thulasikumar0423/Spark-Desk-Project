package com.example.SparkDesk.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "system_assets")
public class SystemAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "owner_team")
    private String ownerTeam;

    @Column(name = "last_checked_at")
    private LocalDateTime lastCheckedAt;

    public enum Status {
        READY_TO_DEPLOY,
        DEPLOYED,
        NEEDS_TROUBLESHOOTING
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    // Getters and setters
}
