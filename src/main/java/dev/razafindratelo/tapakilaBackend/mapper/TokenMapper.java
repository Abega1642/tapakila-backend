package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper {

    public static AccessToken mapAccessTokenFrom(ResultSet rs) throws SQLException {
        return new AccessToken(
                rs.getString("user_email"),
                rs.getTimestamp("access_token_creation_datetime").toLocalDateTime(),
                rs.getTimestamp("access_token_expiration_datetime").toLocalDateTime(),
                rs.getBoolean("access_token_is_valid"),
                rs.getString("access_token")
        );
    }

    public static RefreshToken mapRefreshTokenFrom(ResultSet rs) throws SQLException {
        return new RefreshToken(
                rs.getString("user_email"),
                rs.getTimestamp("refresh_token_creation_datetime").toLocalDateTime(),
                rs.getTimestamp("refresh_token_expiration_datetime").toLocalDateTime(),
                rs.getBoolean("refresh_token_is_valid"),
                rs.getString("refresh_token")
        );
    }
}
