package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findByRoleTarget(String roleTarget);
}
