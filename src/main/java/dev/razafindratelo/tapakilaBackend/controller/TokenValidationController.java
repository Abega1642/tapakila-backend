package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.TokenValidation;
import dev.razafindratelo.tapakilaBackend.service.jwtService.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token-checker")
@AllArgsConstructor
public class TokenValidationController {
    private TokenService tokenService;

    @GetMapping("/access-token")
    public ResponseEntity<TokenValidation> getTokenValidation(HttpServletRequest request) {
        return ResponseEntity.ok(tokenService.validateToken(request));
    }

    @GetMapping("/refresh-token/{refreshToken}")
    public ResponseEntity<TokenValidation> getTokenValidation(@PathVariable String refreshToken) {
        return ResponseEntity.ok(tokenService.validateRefreshToken(refreshToken));
    }
}
