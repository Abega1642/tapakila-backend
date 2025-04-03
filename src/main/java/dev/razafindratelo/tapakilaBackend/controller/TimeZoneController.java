package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/time-zones")
@NoArgsConstructor
public class TimeZoneController {

    @GetMapping
    public ResponseEntity<List<TimeZone>> getAllTimeZones() {
        return ResponseEntity.ok(Arrays.stream(TimeZone.values()).toList());
    }
}
