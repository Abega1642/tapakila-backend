package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.FilterDto;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.service.eventService.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("events")
    public ResponseEntity<List<Event>> findAllEvents(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventService.findAll(page, size));
    }

    @GetMapping("event/{eventId}")
    public ResponseEntity<Event> findEventById(@PathVariable("eventId") String eventId) {
        return ResponseEntity.ok(eventService.findById(eventId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("events/user/{userId}")
    public ResponseEntity<List<Event>> findAllEventsByAdmin(@PathVariable("userId") String userId) {
        throw new NotImplementedException("Find all events by admin not implemented");
    }

    @GetMapping("/events/filter")
    public ResponseEntity<List<Event>> findAllEventsByFilters(
            @RequestBody List<FilterDto> filters,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
    	return ResponseEntity.ok(eventService.findEventsByFilters(filters,page,size));
    }

}
