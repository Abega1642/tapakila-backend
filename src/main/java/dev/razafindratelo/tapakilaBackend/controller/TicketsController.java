package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.service.ticketsService.TicketsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class TicketsController {
    private final TicketsService ticketsService;

    @PostMapping("/buy-tickets")
    public ResponseEntity<List<Tickets>> create(@RequestBody List<TicketPurchase> tktRequests) throws IOException {
        return new ResponseEntity<>(ticketsService.saveAll(tktRequests), HttpStatus.CREATED);
    }

    @GetMapping("/tickets/{userEmail}")
    public ResponseEntity<List<Tickets>> findAllByUserEmail(
            @PathVariable String userEmail,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(ticketsService.findAllByUserEmail(userEmail, page, size));
    }
}
