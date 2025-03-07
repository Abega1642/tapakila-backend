package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.service.eventService.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/api/events")
    public ResponseEntity<List<Event>> findAllEvents(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventService.findAll(page, size));
    }

    @GetMapping("/api/event/{eventId}")
    public ResponseEntity<Event> findEventById(@PathVariable("eventId") String eventId) {
        return ResponseEntity.ok(eventService.findById(eventId));
    }

}
