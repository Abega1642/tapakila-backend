package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class AccountActivationMapper implements Mapper<AccountActivation>{
    private final ObjectMapper objectMapper;

    @Override
    public AccountActivation mapFrom(ResultSet rs) throws SQLException {
        String userJson = rs.getString("account_activation_corresponding_user");
        User user = null;

        try {
            if (userJson != null && !userJson.isBlank()) {
                user = objectMapper.readValue(userJson, User.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime activationTime = (rs.getTimestamp("account_activation_activation") != null)
                ? rs.getTimestamp("account_activation_activation").toLocalDateTime()
                : null;

        assert user != null;
        user.setFavoriteEventCategories(List.of());
        user.setPassword("__sorry_but_you_cannot_have_a_look_at_the_user_password__");

        return new AccountActivation (
                rs.getString("account_activation_id"),
                user,
                rs.getString("account_activation_code"),
                rs.getTimestamp("account_activation_creation").toLocalDateTime(),
                rs.getTimestamp("account_activation_expiration").toLocalDateTime(),
                activationTime,
                rs.getBoolean("account_activation_is_active")
        );
    }
}
