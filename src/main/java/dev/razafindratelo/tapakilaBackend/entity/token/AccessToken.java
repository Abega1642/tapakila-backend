package dev.razafindratelo.tapakilaBackend.entity.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AccessToken extends Token {
    private String accessToken;

    public AccessToken(
            String userEmail,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            boolean isValid,
            String accessToken
    ) {
        super(userEmail, createdAt, expiresAt, isValid);
        this.accessToken = accessToken;
    }
}
