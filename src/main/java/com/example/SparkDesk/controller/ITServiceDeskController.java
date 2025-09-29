package com.example.SparkDesk.controller;

import com.example.SparkDesk.dto.ticket.SupportTicketDTO;
import com.example.SparkDesk.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "API")
@RequestMapping("/api/it")
@RequiredArgsConstructor
public class ITServiceDeskController {

    private final SupportService supportService;

    @PreAuthorize("hasRole('IT_DESK')")
    @GetMapping("/tickets/open")
    public ResponseEntity<List<SupportTicketDTO>> getOpenTickets() {
        return ResponseEntity.ok(supportService.getOpenTickets());
    }

    @PreAuthorize("hasRole('IT_DESK')")
    @GetMapping("/tickets/completed")
    public ResponseEntity<List<SupportTicketDTO>> getCompletedTickets() {
        return ResponseEntity.ok(supportService.getCompletedTickets());
    }

    @PreAuthorize("hasRole('IT_DESK')")
    @PostMapping("/tickets/{id}/resolve")
    public ResponseEntity<SupportTicketDTO> resolveTicket(@PathVariable Long id, @RequestParam Long resolverId) {
        return ResponseEntity.ok(supportService.resolveTicket(id, resolverId));
    }
}
