package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/event-categories")
public class EventCategoryController {

    @GetMapping
    public ResponseEntity<List<EventCategory>> getAllEventCategoriesEnums() {
        return ResponseEntity.ok(Arrays.stream(EventCategory.values()).toList());
    }
}
