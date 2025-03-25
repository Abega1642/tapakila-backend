package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class FileDao {
    private final DataSource dataSource;

    public String findProfileByUserEmail(String email) {
        String sqlRequest =
                """
                SELECT profile_img_path FROM "user" WHERE email = ?
                """;

        Connection connection = dataSource.getConnection(FileDao.class.getName());

        try(PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {
            sqlStmt.setString(1, email);
            ResultSet resultSet = sqlStmt.executeQuery();

            if (resultSet.next())
                return resultSet.getString(1);

            throw new SQLException("FileDao.findUserProfileByUserEmail :: could not find profile img path");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "FileDao.findProfileByUserEmail :: " + e.getMessage()
            );
        }
    }

    public String findQRCodePath(String email) {
        String sqlRequest =
                """
                SELECT qr_code_path FROM ticket WHERE user_email = ?
                """;

        Connection connection = dataSource.getConnection(FileDao.class.getName());

        try(PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {
            throw new NotImplementedException("Not implemented yet");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "FileDao.findProfileByUserEmail :: " + e.getMessage()
            );
        }
    }
}
