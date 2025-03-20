package dev.razafindratelo.tapakilaBackend.service.jwtService;

import dev.razafindratelo.tapakilaBackend.dto.logout.LogOutDto;
import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import dev.razafindratelo.tapakilaBackend.entity.token.Token;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public interface TokenService {
    AccessToken findByValue(String accessToken);
    RefreshToken findByCreationExpirationDateAndUserEmail(LocalDateTime creation, LocalDateTime expiration, String userEmail);
    LogOutDto disableTokens(String accessToken);
    RefreshToken findByRefreshToken(String refreshToken);
    Map<String, Token> saveTokens(AccessToken accessToken, RefreshToken refreshToken);
}
