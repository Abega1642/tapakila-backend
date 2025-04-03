package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/event-types-enum")
@NoArgsConstructor
public class EventTypeEnumController {

    @GetMapping
    public ResponseEntity<List<EventType>> getAllEventTypesEnums() {
        return ResponseEntity.ok(Arrays.stream(EventType.values()).toList());
    }
}
