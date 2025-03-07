package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements Mapper<User>{

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
                "__password__",
                UserRole.valueOf(rs.getString("user_role")),
                rs.getBoolean("user_status"),
                top5Categories
        );
    }
}
