package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ticket-types")
public class TicketTypeController {

    @GetMapping
    public ResponseEntity<List<TicketType>> getAllTicketTypesEnums() {
        return ResponseEntity.ok(Arrays.stream(TicketType.values()).toList());
    }
}
