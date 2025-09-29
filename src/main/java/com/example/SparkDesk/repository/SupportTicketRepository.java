package com.example.SparkDesk.repository;

import com.example.SparkDesk.model.SupportTicket;
import com.example.SparkDesk.model.SupportTicket.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByStatus(Status open);
    List<SupportTicket> findByAssignedToItId(Long itDeskUserId);
}
