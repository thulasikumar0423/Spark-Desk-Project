package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.PasswordHistory;
import com.example.SparkDesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
    List<PasswordHistory> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
