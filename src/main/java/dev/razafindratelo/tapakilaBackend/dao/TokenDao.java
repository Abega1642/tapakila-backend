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
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Component
public class TokenDao {
    private final DataSource dataSource;

    public Optional<RefreshToken> findRefreshTokenByValue(String token) {
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
        Connection connection = dataSource.getConnection(TokenDao.class.getName());

        try(PreparedStatement findAccessTokenStmt = connection.prepareStatement(sqlQuery)) {
            findAccessTokenStmt.setString(1, token);

            ResultSet rs = findAccessTokenStmt.executeQuery();

            if (rs.next())
                return Optional.of(TokenMapper.mapRefreshTokenFrom(rs));

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "TokenDao.findRefreshTokenByValue :: %s",
                            e.getMessage()
                    )
            );
        }
    }

    public Optional<RefreshToken> findRefreshTokenByCreationAndExpirationDateTimeAndUserEmail(
            LocalDateTime creationDateTime, LocalDateTime expirationDateTime, String email
    ) {
        String sqlQuery =
                """
                SELECT
                    rt.user_email AS user_email,
                    rt.created_at AS refresh_token_creation_datetime,
                    rt.expires_at AS refresh_token_expiration_datetime,
                    rt.is_valid AS refresh_token_is_valid,
                    rt.refresh_token AS refresh_token
                FROM refresh_token rt
                WHERE rt.created_at = (?::timestamp)
                    AND rt.expires_at = (?::timestamp)
                    AND rt.user_email = ?
                """;
        Connection connection = dataSource.getConnection(TokenDao.class.getName());

        try (PreparedStatement findByCreationAndExpirationAndUserEmailStmt = connection.prepareStatement(sqlQuery)) {

            findByCreationAndExpirationAndUserEmailStmt.setTimestamp(1, Timestamp.valueOf(creationDateTime));
            findByCreationAndExpirationAndUserEmailStmt.setTimestamp(2, Timestamp.valueOf(expirationDateTime));
            findByCreationAndExpirationAndUserEmailStmt.setString(3, email);

            ResultSet rs = findByCreationAndExpirationAndUserEmailStmt.executeQuery();

            if (rs.next())
                return Optional.of(TokenMapper.mapRefreshTokenFrom(rs));

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "TokenDao.findRefreshTokenByCreationAndExpirationDateTimeAndUserEmail :: %s",
                            e.getMessage()
                    )
            );
        }
    }

    public Optional<AccessToken> findAccessTokenByValue(String token) {
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
        Connection connection = dataSource.getConnection(TokenDao.class.getName());

        try(PreparedStatement findAccessTokenStmt = connection.prepareStatement(sqlQuery)) {
            findAccessTokenStmt.setString(1, token);

            ResultSet rs = findAccessTokenStmt.executeQuery();

            if (rs.next())
                return Optional.of(TokenMapper.mapAccessTokenFrom(rs));

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "TokenDao.findAccessTokenByValue :: %s",
                            e.getMessage()
                    )
            );
        }
    }

    public boolean disableTokens(String accessToken, String refreshToken) {
        String sqlRequestForDisablingAccessToken =
                """
                UPDATE access_token SET is_valid = false WHERE access_token = ?
                """;
        String sqlRequestForDisablingRefreshToken =
                """
                UPDATE refresh_token SET is_valid = false WHERE refresh_token = ?
                """;
        Connection connection = dataSource.getConnection(TokenDao.class.getName());

        try (PreparedStatement disableAccessTokenStmt = connection.prepareStatement(sqlRequestForDisablingAccessToken);
            PreparedStatement disableRefreshTokenStmt = connection.prepareStatement(sqlRequestForDisablingRefreshToken)
        ) {
            disableAccessTokenStmt.setString(1, accessToken);
            disableRefreshTokenStmt.setString(1, refreshToken);

            int accessTokenUpdatedRows = disableAccessTokenStmt.executeUpdate();
            int refreshTokenUpdatedRows = disableRefreshTokenStmt.executeUpdate();

            return accessTokenUpdatedRows == 1 && refreshTokenUpdatedRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "TokenDao.disableTokens :: %s",
                            e.getMessage()
                    )
            );
        }
    }

    public Optional<Map<String, Token>> saveToken(AccessToken accessToken, RefreshToken refreshToken) {
        String saveAccessTokenSQLQuery =
                """
                INSERT INTO access_token VALUES (?, ?, ?, ?, ?);
                """;
        String saveRefreshTokenSQLQuery =
                """
                INSERT INTO refresh_token VALUES (?, ?, ?, ?, ?);
                """;
        Connection connection = dataSource.getConnection(TokenDao.class.getName());

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

            if (accessTokenUpdate > 0 && refreshTokenUpdate > 0)
                return Optional.of(Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken
                ));

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "TokenDao.saveToken :: %s",
                            e.getMessage()
                    )
            );
        }
    }

}
