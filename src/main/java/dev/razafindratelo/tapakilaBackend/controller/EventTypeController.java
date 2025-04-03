package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.service.eventTypeDetailService.EventTypeDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class EventTypeController {
    private final EventTypeDetailService eventTypeDetailService;

    @GetMapping("event-types")
    public ResponseEntity<List<EventTypeDetail>> findAllEventTypes (
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size
    ) {
        return ResponseEntity.ok(eventTypeDetailService.findAll(page, size));
    }

    @GetMapping("event-type/{eventTypeId}")
    public ResponseEntity<EventTypeDetail> findEventTypeById (@PathVariable("eventTypeId") String eventTypeId) {
        return ResponseEntity.ok(eventTypeDetailService.findById(eventTypeId));
    }
}
