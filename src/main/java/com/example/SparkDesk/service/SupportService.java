package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.ticket.SupportTicketDTO;

import java.util.List;

public interface SupportService {
    SupportTicketDTO createTicket(SupportTicketDTO dto, Long userId);
    List<SupportTicketDTO> getOpenTickets();
    List<SupportTicketDTO> getCompletedTickets();
    SupportTicketDTO resolveTicket(Long ticketId, Long resolverId);
}
