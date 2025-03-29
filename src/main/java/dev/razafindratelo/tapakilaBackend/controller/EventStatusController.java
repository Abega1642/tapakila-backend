package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@RestController
@RequestMapping("/events-status")
public class EventStatusController {

    @GetMapping
    public ResponseEntity<List<EventStatus>> getAllEventStatus() {
        return ResponseEntity.ok(Arrays.stream(EventStatus.values()).collect(Collectors.toList()));
    }
}
