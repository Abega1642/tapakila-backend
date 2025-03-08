package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountActivation {
    private String id;
    @JsonProperty("corresponding_user")
    private User user;
    private String activationCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime activatedAt;
    private boolean isActive;

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
