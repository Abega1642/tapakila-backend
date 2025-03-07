package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size,
            @RequestParam(value = "username", required = false) String username
    ) {
        return ResponseEntity.ok(userService.findAllUserByUsername(username, page, size));
    }

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<User> findUserByEmail(@PathVariable("userEmail") String userEmail) {
        return ResponseEntity.ok(userService.findByEmail(userEmail));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
