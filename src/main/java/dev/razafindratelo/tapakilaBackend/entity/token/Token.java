package dev.razafindratelo.tapakilaBackend.entity.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public abstract class Token {
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isValid;
}
