package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService.TicketPriceInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ticket-price-infos")
public class TicketPriceInfoController {
    private final TicketPriceInfoService ticketPriceInfoService;

    @GetMapping("/{eventId}")
    public ResponseEntity<List<TicketPriceInfo>> getAllTicketPriceInfosOfEvent(
            @PathVariable("eventId") String eventId,
            @RequestParam(value = "at", required = false) LocalDate at
    ) {
        return ResponseEntity.ok(ticketPriceInfoService.getAllTicketPriceInfosByEventIdAtAGivenDate(eventId, at));
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<List<TicketPriceInfo>> saveTicketPriceInfosOfEvent(
            @PathVariable String eventId,
            @RequestBody List<TicketPriceInfo> ticketPriceInfos
    ) {
        return new ResponseEntity<>(ticketPriceInfoService.saveTicketPriceInfos(ticketPriceInfos, eventId), HttpStatus.CREATED);
    }
}
