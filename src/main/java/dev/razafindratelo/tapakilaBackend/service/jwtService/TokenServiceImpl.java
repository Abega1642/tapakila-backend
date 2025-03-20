package dev.razafindratelo.tapakilaBackend.service.jwtService;

import dev.razafindratelo.tapakilaBackend.dao.TokenDao;
import dev.razafindratelo.tapakilaBackend.dto.logout.LogOutDto;
import dev.razafindratelo.tapakilaBackend.dto.logout.LogOutStatus;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import dev.razafindratelo.tapakilaBackend.entity.token.Token;
import dev.razafindratelo.tapakilaBackend.exception.ActionNotAllowedException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
@Getter
public class TokenServiceImpl implements TokenService {
    private final TokenDao tokenDao;

    @Override
    public AccessToken findByValue(String accessToken) {
        if (accessToken == null || accessToken.trim().isEmpty())
            throw new IllegalArgumentException("TokenService :: Access token cannot be null or empty");

        return tokenDao.findAccessTokenByValue(accessToken)
                .orElseThrow(() -> new ResourceNotFoundException("TokenService :: Access token not found"));
    }

    @Override
    public RefreshToken findByCreationExpirationDateAndUserEmail(LocalDateTime creation, LocalDateTime expiration, String userEmail) {
        if (creation == null || expiration == null || userEmail == null)
            throw new IllegalArgumentException("TokenService :: Creation or expiration date or email cannot be null");

        if (creation.isAfter(expiration))
            throw new IllegalArgumentException("TokenService :: Creation expiration date cannot be after expiration date");

        if (userEmail.trim().isEmpty())
            throw new IllegalArgumentException("TokenService :: User email cannot be null or empty");

        return tokenDao.findRefreshTokenByCreationAndExpirationDateTimeAndUserEmail(
                creation,
                expiration,
                userEmail.trim()
        ).orElseThrow(() -> new ResourceNotFoundException("TokenService :: Refresh token not found"));
    }

    @Override
    public LogOutDto disableTokens(String accessToken) {
        AccessToken accessTokenUsed = findByValue(accessToken);

        RefreshToken refreshTokenUsed = findByCreationExpirationDateAndUserEmail(
                accessTokenUsed.getCreatedAt(),
                accessTokenUsed.getExpiresAt().plusDays(2), // because the refresh token expires 2 days after the access token
                accessTokenUsed.getUserEmail()
        );


        boolean areDisabled = tokenDao.disableTokens(accessTokenUsed.getAccessToken(), refreshTokenUsed.getRefreshToken());

        LogOutStatus status = (areDisabled) ? LogOutStatus.SUCCESS
                : LogOutStatus.ERROR;
        String message = status.getMessage();

        LogOutDto logOut = new LogOutDto(
                accessTokenUsed.getUserEmail(),
                status,
                message
        );

        if (!areDisabled)
            throw new RuntimeException(logOut.toString());

        return logOut;
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty())
            throw new IllegalArgumentException("TokenService :: Refresh token cannot be null or empty");

        return tokenDao.findRefreshTokenByValue(refreshToken)
                .orElseThrow(() -> new ResourceNotFoundException("TokenService :: Refresh token not found"));
    }



    @Override
    public Map<String, Token> saveTokens(AccessToken accessToken, RefreshToken refreshToken) {
        if (accessToken == null || refreshToken == null)
            throw new IllegalArgumentException("TokenService :: Access token and refresh token cannot be null");

        if (accessToken.getAccessToken() == null || accessToken.getAccessToken().trim().isEmpty())
            throw new IllegalArgumentException("TokenService :: Access token cannot be null or empty");

        if (refreshToken.getRefreshToken() == null || refreshToken.getRefreshToken().trim().isEmpty())
            throw new IllegalArgumentException("ToKenService :: Refresh token cannot be null or empty");

        return tokenDao.saveToken(accessToken, refreshToken)
                .orElseThrow(() -> new RuntimeException("TokenService :: Error while saving token"));
    }
}
