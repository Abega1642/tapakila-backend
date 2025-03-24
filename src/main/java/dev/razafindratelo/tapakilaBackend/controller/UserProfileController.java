package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.service.userProfileService.UserProfileService;
import lombok.AllArgsConstructor;
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
        throw new NotImplementedException("Not implemented yet");
    }
}
