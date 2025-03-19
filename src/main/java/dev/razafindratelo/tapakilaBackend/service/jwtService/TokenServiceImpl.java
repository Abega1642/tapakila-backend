package dev.razafindratelo.tapakilaBackend.service.jwtService;

import dev.razafindratelo.tapakilaBackend.dao.TokenDao;
import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import dev.razafindratelo.tapakilaBackend.entity.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
@Getter
public class TokenServiceImpl implements TokenService {
    private final TokenDao tokenDao;

    @Override
    public AccessToken findByValue(String accessToken) {
        if (accessToken == null || accessToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Access token cannot be null or empty");
        }
        return tokenDao.findAccessTokenByValue(accessToken);
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Refresh token cannot be null or empty");
        }
        return tokenDao.findRefreshTokenByValue(refreshToken);
    }

    @Override
    public Map<String, Token> saveTokens(AccessToken accessToken, RefreshToken refreshToken) {
        if (accessToken == null || refreshToken == null) {
            throw new IllegalArgumentException("Access token and refresh token cannot be null");
        }
        if (accessToken.getAccessToken() == null || accessToken.getAccessToken().trim().isEmpty()) {
            throw new IllegalArgumentException("Access token cannot be null or empty");
        }
        if (refreshToken.getRefreshToken() == null || refreshToken.getRefreshToken().trim().isEmpty()) {
            throw new IllegalArgumentException("Refresh token cannot be null or empty");
        }
        return tokenDao.saveToken(accessToken, refreshToken);
    }
}
