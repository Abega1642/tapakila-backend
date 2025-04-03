package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.service.imgServices.userProfileService.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/user/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/{userEmail}")
    public ResponseEntity<byte[]> getUserProfile(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(userProfileService.findUserProfileByUserEmail(userEmail));
    }

    @PostMapping("/update/{userEmail}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable("userEmail") String userEmail,
            @RequestBody MultipartFile profile
    ) {
        return ResponseEntity.ok(userProfileService.updateProfileImageByUserEmail(userEmail, profile));
    }
}
