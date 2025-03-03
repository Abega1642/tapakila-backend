package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements Mapper<User>{
    @Override
    public User mapFrom(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("user_email"),
                rs.getString("user_last_name"),
                rs.getString("user_first_name"),
                rs.getString("user_img_profil_path"),
                "__password__",
                UserRole.valueOf(rs.getString("user_role")),
                rs.getBoolean("user_status"),
                List.of()
        );
    }
}
