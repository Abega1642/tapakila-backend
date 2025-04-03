package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Random;


@NoArgsConstructor
@Data
public class AccountActivation {
    private String id;
    @JsonProperty("correspondingUser")
    private User user;
    private String activationCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime activatedAt;
    private boolean isActive;

    public AccountActivation(
            String id,
            User user,
            String activationCode,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime activatedAt,
            boolean isActive
    ) {
        this.id = id;
        this.user = user;
        this.activationCode = activationCode;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.activatedAt = activatedAt;
        this.isActive = isActive;
    }

    public AccountActivation(
            String id,
            User user,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime activatedAt,
            boolean isActive
    ) {
        this.id = id;
        this.user = user;
        this.activationCode = generateActivationCode();
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.activatedAt = activatedAt;
        this.isActive = isActive;
    }

    public AccountActivation(String id, User user) {
        this.id = id;
        this.user = user;
        this.activationCode = generateActivationCode();
    }

    private static String generateActivationCode() {
        Random random = new Random();
        return String.format(
                "%09d", random.nextInt(999_999_999)
        );
    }

    @Override
    public String toString() {
        return "AccountActivation {" +
                "\n \t id='" + id + '\'' +
                ",\n \t user=" + user +
                ",\n \t activationCode='" + activationCode + '\'' +
                ",\n \t createdAt=" + createdAt +
                ",\n \t updatedAt=" + expiredAt +
                ",\n \t activatedAt=" + activatedAt +
                ",\n \t isActive=" + isActive +

                "\n \t }";
    }
}
