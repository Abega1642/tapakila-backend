package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
public class UserMapper implements Mapper<User>{
    private final ObjectMapper objectMapper;

    @Override
    public User mapFrom(ResultSet rs) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String top5CategoriesJSON = rs.getString("user_top_5_categories");
        List<EventCategoryDetail> top5Categories = EventTypeDetailMapper.mapEventDetailCategoryJSONB(objectMapper, top5CategoriesJSON);

        return new User(
                rs.getString("user_email"),
                rs.getString("user_last_name"),
                rs.getString("user_first_name"),
                rs.getString("user_img_profil_path"),
                rs.getString("user_password"),
                UserRole.valueOf(rs.getString("user_role")),
                rs.getBoolean("user_status"),
                rs.getTimestamp("user_creation_date").toLocalDateTime(),
                top5Categories
        );
    }

    public User mapFromJson(String json) throws JsonProcessingException {
        User user = null;

        try {
            if (json != null && !json.isEmpty()) {
                user = objectMapper.readValue(json, User.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur de parsing JSON pour correspondingTicketType : " + e.getMessage(), e);
        }

        return user;
    }
}
