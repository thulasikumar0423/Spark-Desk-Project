package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.ticket.SupportTicketDTO;
import com.example.SparkDesk.exception.TicketNotFoundException;
import com.example.SparkDesk.model.SupportTicket;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.SupportTicketRepository;
import com.example.SparkDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SupportServiceImpl implements SupportService {

    private final SupportTicketRepository ticketRepository = null;
    private final UserRepository userRepository = null;

    @Override
    public SupportTicketDTO createTicket(SupportTicketDTO dto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new TicketNotFoundException("User not found"));

        SupportTicket ticket = new SupportTicket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setRaisedBy(user);
        ticket.setStatus(SupportTicket.Status.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());

        ticketRepository.save(ticket);

        return mapToDTO(ticket);
    }

    @Override
    public List<SupportTicketDTO> getOpenTickets() {
        List<SupportTicket> tickets = ticketRepository.findByStatus(SupportTicket.Status.OPEN);
        return tickets.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SupportTicketDTO> getCompletedTickets() {
        List<SupportTicket> tickets = ticketRepository.findByStatus(SupportTicket.Status.RESOLVED);
        return tickets.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public SupportTicketDTO resolveTicket(Long ticketId, Long resolverId) {
        SupportTicket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        User resolver = userRepository.findById(resolverId).orElseThrow(() -> new TicketNotFoundException("Resolver not found"));

        ticket.setStatus(SupportTicket.Status.RESOLVED);
        ticket.setAssignedToIt(resolver);
        ticket.setResolvedAt(LocalDateTime.now());

        ticketRepository.save(ticket);

        return mapToDTO(ticket);
    }

    private SupportTicketDTO mapToDTO(SupportTicket ticket) {
        return new SupportTicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getRaisedBy() != null ? ticket.getRaisedBy().getId() : null,
                ticket.getAssignedToIt() != null ? ticket.getAssignedToIt().getId() : null,
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getResolvedAt()
        );
    }
}
