package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.service.userProfileService.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/user/profile/{userEmail}")
    public ResponseEntity<byte[]> getUserProfile(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(userProfileService.findUserProfileByUserEmail(userEmail));
    }
}
