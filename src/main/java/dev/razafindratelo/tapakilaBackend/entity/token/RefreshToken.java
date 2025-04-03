package dev.razafindratelo.tapakilaBackend.entity.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RefreshToken extends Token {
    private String refreshToken;

    public RefreshToken(
            String userEmail,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            boolean isValid,
            String refreshToken
    ) {
        super(userEmail, createdAt, expiresAt, isValid);
        this.refreshToken = refreshToken;
    }

    public RefreshToken(String userEmail, LocalDateTime createdAt, LocalDateTime expiresAt, boolean isValid) {
        super(userEmail, createdAt, expiresAt, isValid);
        this.refreshToken = generateRefreshToken();
    }

    private static String generateRefreshToken() {
        return "$Trf-"+ UUID.randomUUID();
    }
}
