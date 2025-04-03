package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.*;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.service.eventDescriptionService.EventDescriptionService;
import dev.razafindratelo.tapakilaBackend.service.eventService.EventService;
import dev.razafindratelo.tapakilaBackend.service.eventTitleService.EventTitleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventDescriptionService eventDescriptionService;
    private final EventTitleService eventTitleService;

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

    @PostMapping("/events/filter")
    public ResponseEntity<List<Event>> findAllEventsByFilters(
            @RequestBody List<FilterDto> filters,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
    	return ResponseEntity.ok(eventService.findEventsByFilters(filters,page,size));
    }

    @GetMapping("/events/descriptions")
    public ResponseEntity<List<EventDescription>> findAllEventDescriptions(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventDescriptionService.findAllEvDescription(page, size));
    }

    @GetMapping("/events/titles")
    public ResponseEntity<List<EventTitle>> findAllEventTitles(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventTitleService.findAllEventTitles(page, size));
    }

    @PostMapping("/events/create")
    public ResponseEntity<Event> saveEvent(
            @RequestBody EventDto event
    ) {
        return new ResponseEntity<>(eventService.save(event), HttpStatus.CREATED);
    }

    @PutMapping("/event/update/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable String eventId, @RequestBody List<UpdatePayload> updatePayloads) {
        return ResponseEntity.ok(eventService.updateEvent(updatePayloads, eventId));
    }

    @GetMapping("/events/total-count")
    public ResponseEntity<Map<String, Long>> getTotalEventCount() {
        return ResponseEntity.ok(Map.of("totalNumber", eventService.findTotalEvent()));
    }
}
