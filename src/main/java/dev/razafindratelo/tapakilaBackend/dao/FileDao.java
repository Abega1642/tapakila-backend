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

    public String updateProfileImageByUserEmail(String email, String imagePath) {
        Connection connection = dataSource.getConnection(FileDao.class.getName());
        String sqlRequest =
                """
                UPDATE "user" SET profile_img_path = ? WHERE email = ?
                """;
        try (PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {
            sqlStmt.setString(1, imagePath);
            sqlStmt.setString(2, email);

            if (sqlStmt.executeUpdate() > 0)
                return imagePath;

            throw new SQLException("FileDao.updateProfileImageByUserEmail:: could not update profile img path");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "FileDao.updateProfileImageByUserEmail :: " + e.getMessage()
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

    public String findEventImgById(String id) {
        String sqlRequest =
                """
                SELECT image_path FROM event WHERE id = ?
                """;

        Connection connection = dataSource.getConnection(FileDao.class.getName());

        try(PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {

            sqlStmt.setString(1, id);
            ResultSet resultSet = sqlStmt.executeQuery();

            if (resultSet.next())
                return resultSet.getString(1);

            throw new SQLException("FileDao.findEventImgById :: could not event img path");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "FileDao.findEventImgById :: " + e.getMessage()
            );
        }
    }

    public String updateEventImgByIdAndPath(String path, String eventId) {
        Connection connection = dataSource.getConnection(FileDao.class.getName());
        String sqlRequest =
                """
                UPDATE event SET image_path = ? WHERE id = ?
                """;
        try (PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {
            sqlStmt.setString(1, path);
            sqlStmt.setString(2, eventId);

            if (sqlStmt.executeUpdate() > 0)
                return path;

            throw new SQLException("FileDao.updateEventImgByIdAndPath:: could not update event img path");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "FileDao.updateEventImgByIdAndPath :: " + e.getMessage()
            );
        }
    }
}
