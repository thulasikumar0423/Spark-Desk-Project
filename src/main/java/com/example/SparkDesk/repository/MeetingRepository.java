package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByScheduledAtAfter(LocalDateTime dateTime);
}
