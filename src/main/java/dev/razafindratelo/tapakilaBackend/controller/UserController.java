package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.Login;
import dev.razafindratelo.tapakilaBackend.dto.UserUpdatePassword;
import dev.razafindratelo.tapakilaBackend.dto.ValidationCode;
import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.exception.ActionNotAllowedException;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final AuthenticationManager authManager;
    private final UserService userService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) throws MessagingException {
        return new ResponseEntity<>(userService.signUp(user), HttpStatus.CREATED);
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<JwtDTO> signIn(@RequestBody Login login) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password())
        );

        if (!auth.isAuthenticated())
            throw new ActionNotAllowedException("Authentication failed for email " + login.email());

        return ResponseEntity.ok(userService.signIn(login));
    }

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

    @PatchMapping("/user/activate-account")
    public ResponseEntity<User> activateAccount(@RequestBody ValidationCode validationCode) {
        return new ResponseEntity<>(userService.activateAccount(validationCode), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/user/update-password")
    public ResponseEntity<User> updateUserPassword(
            @RequestBody UserUpdatePassword userUpdatePassword
    ) {
        return new ResponseEntity<>(userService.updateUserPassword(userUpdatePassword), HttpStatus.CREATED);
    }
}
