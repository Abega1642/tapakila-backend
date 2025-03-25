package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.service.imgServices.eventImgService.EventImgService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class EventImgController {
    private final EventImgService eventImgService;

    @GetMapping("/event/image/{eventId}")
    public ResponseEntity<byte[]> getEventImgByEventId(@PathVariable("eventId") String eventId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(eventImgService.findEventByEventId(eventId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/event/update/image/{eventId}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable("eventId") String eventId,
            @RequestBody MultipartFile profile
    ) {
        return ResponseEntity.ok(eventImgService.updateEventImgByIdAndPath(eventId, profile));
    }
}
