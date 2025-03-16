package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.EventTurnover;
import dev.razafindratelo.tapakilaBackend.service.eventTurnoverService.EventTurnoverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class EventTurnoverController {
    private final EventTurnoverService eventTurnoverService;

    @GetMapping("events-turnover")
    public ResponseEntity<List<EventTurnover>> finAllEventTurnovers(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventTurnoverService.finAll(page, size));
    }

    @GetMapping("events-turnover/{date}")
    public ResponseEntity<List<EventTurnover>> finAllEventTurnoversAtDate(
            @PathVariable("date") LocalDate date,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size",required = false) Long size
    ) {
        return ResponseEntity.ok(eventTurnoverService.finAllAtAGivenDate(date, page, size));
    }

    @GetMapping("event-turnover/{eventId}")
    public ResponseEntity<EventTurnover> findEventTurnoverById(@PathVariable("eventId") String eventId) {
        return ResponseEntity.ok(eventTurnoverService.findById(eventId));
    }
}
