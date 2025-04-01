package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/user-roles")
@NoArgsConstructor
public class UserRoleController {

    @GetMapping
    public ResponseEntity<List<UserRole>> getUserRoles() {
        return ResponseEntity.ok(Arrays.stream(UserRole.values()).toList());
    }
}
