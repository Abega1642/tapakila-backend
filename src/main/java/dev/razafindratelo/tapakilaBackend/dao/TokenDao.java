package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.token.AccessToken;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import dev.razafindratelo.tapakilaBackend.entity.token.Token;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.mapper.TokenMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.Map;

@AllArgsConstructor
@Getter
@Component
public class TokenDao {
    private final DataSource dataSource;

    public RefreshToken findRefreshTokenByValue(String token) {
        String sqlQuery =
                """
                SELECT
                    rt.user_email AS user_email,
                    rt.created_at AS refresh_token_creation_datetime,
                    rt.expires_at AS refresh_token_expiration_datetime,
                    rt.is_valid AS refresh_token_is_valid,
                    rt.refresh_token AS refresh_token
                FROM refresh_token rt
                WHERE rt.refresh_token = ?
                """;
        Connection connection = dataSource.getConnection();

        try(PreparedStatement findAccessTokenStmt = connection.prepareStatement(sqlQuery)) {

            ResultSet rs = findAccessTokenStmt.executeQuery();
            if (rs.next()) {
                return TokenMapper.mapRefreshTokenFrom(rs);
            }
            throw new ResourceNotFoundException("Refresh token not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AccessToken findAccessTokenByValue(String token) {
        String sqlQuery =
                """
                SELECT
                    at.user_email AS user_email,
                    at.created_at AS access_token_creation_datetime,
                    at.expires_at AS access_token_expiration_datetime,
                    at.is_valid AS access_token_is_valid,
                    at.access_token AS access_token
                FROM access_token at
                WHERE at.access_token = ?
                """;
        Connection connection = dataSource.getConnection();

        try(PreparedStatement findAccessTokenStmt = connection.prepareStatement(sqlQuery)) {

            ResultSet rs = findAccessTokenStmt.executeQuery();
            if (rs.next()) {
                return TokenMapper.mapAccessTokenFrom(rs);
            }
            throw new ResourceNotFoundException("Access token not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Token> saveToken(AccessToken accessToken, RefreshToken refreshToken) {
        String saveAccessTokenSQLQuery =
                """
                INSERT INTO access_token VALUES (?, ?, ?, ?, ?);
                """;
        String saveRefreshTokenSQLQuery =
                """
                INSERT INTO refresh_token VALUES (?, ?, ?, ?, ?);
                """;
        Connection connection = dataSource.getConnection();

        try (PreparedStatement saveAccessTokenStmt = connection.prepareStatement(saveAccessTokenSQLQuery);
            PreparedStatement saveRefreshTokenStmt = connection.prepareStatement(saveRefreshTokenSQLQuery)
        ) {
            saveAccessTokenStmt.setString(1, accessToken.getAccessToken());
            saveRefreshTokenStmt.setString(1, refreshToken.getRefreshToken());

            saveAccessTokenStmt.setTimestamp(2, Timestamp.valueOf(accessToken.getCreatedAt()));
            saveRefreshTokenStmt.setTimestamp(2, Timestamp.valueOf(refreshToken.getCreatedAt()));

            saveAccessTokenStmt.setTimestamp(3, Timestamp.valueOf(accessToken.getExpiresAt()));
            saveRefreshTokenStmt.setTimestamp(3, Timestamp.valueOf(refreshToken.getExpiresAt()));

            saveAccessTokenStmt.setBoolean(4, accessToken.isValid());
            saveRefreshTokenStmt.setBoolean(4, refreshToken.isValid());

            saveAccessTokenStmt.setString(5, accessToken.getUserEmail());
            saveRefreshTokenStmt.setString(5, refreshToken.getUserEmail());
			

            int accessTokenUpdate = saveAccessTokenStmt.executeUpdate();
            int refreshTokenUpdate = saveRefreshTokenStmt.executeUpdate();

            if (accessTokenUpdate > 0 && refreshTokenUpdate > 0) {
                return Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken
                );
            }
            throw new ResourceNotFoundException("Error while saving tokens");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
