package dev.razafindratelo.tapakilaBackend.service.jwtService;

import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
@Getter
public class JwtServiceImpl implements JwtService {
    private final TokenService tokenService;
	private final LocalDateTime DEFAULT_JWT_CREATION_DATE_TIME = LocalDateTime.now();
    private final LocalDateTime DEFAULT_JWT_EXPIRATION_DATE_TIME = DEFAULT_JWT_CREATION_DATE_TIME.plusDays(2);

    @Override
    public JwtDTO generate(User user) {
        Instant NOW = DEFAULT_JWT_CREATION_DATE_TIME.toInstant(ZoneOffset.UTC);

        Date DEFAULT_JWT_CREATION_DATE = Date.from(NOW);
        Date DEFAULT_JWT_EXPIRATION_DATE = Date.from(NOW.plus(2, ChronoUnit.DAYS));

        Map<String, String> claims = getUserClaims(user, DEFAULT_JWT_CREATION_DATE, DEFAULT_JWT_EXPIRATION_DATE);

        String accessToken = Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(DEFAULT_JWT_CREATION_DATE)
                .expiration(DEFAULT_JWT_EXPIRATION_DATE)
                .claims(claims)
                .signWith(signatureKey())
                .compact();

        AccessToken accessTokenRes = new AccessToken(
                user.getEmail(),
                DEFAULT_JWT_CREATION_DATE_TIME,
                DEFAULT_JWT_EXPIRATION_DATE_TIME,
                true,
                accessToken
        );

        RefreshToken refreshTokenRes = new RefreshToken(
                user.getEmail(),
                DEFAULT_JWT_CREATION_DATE_TIME,
                DEFAULT_JWT_EXPIRATION_DATE_TIME,
                true
        );

        tokenService.saveTokens(accessTokenRes, refreshTokenRes);
        return new JwtDTO(user.getEmail(), accessToken, refreshTokenRes.getRefreshToken());
    }

    private Key signatureKey() {
        Dotenv dotenv = Dotenv.load();
        String ENCRYPTION_KEY = dotenv.get("ENCRYPTION_KEY");

        byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    private Map<String, String> getUserClaims(User user, Date creation, Date expiration) {
        return Map.of(
                "email", user.getEmail(),
                "lastName", user.getLastName(),
                "firstName", user.getFirstName(),
                "role", user.getUserRole().toString(),
                "accountCreationDate", user.getCreatedAt().toString(),
                "createdAt", DEFAULT_JWT_CREATION_DATE_TIME.toString(),
                "expiresAt", DEFAULT_JWT_EXPIRATION_DATE_TIME.toString()
        );
    }
}
