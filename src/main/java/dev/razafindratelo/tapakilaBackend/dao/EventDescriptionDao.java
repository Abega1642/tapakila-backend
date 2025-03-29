package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dto.EventDescription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class EventDescriptionDao {
    private final DataSource dataSource;

    public List<EventDescription> findAllEvDescription(long page, long size) {
        String sqlRequest =
                """
                SELECT description, id from event LIMIT ? OFFSET ?
                """;
        Connection connection = dataSource.getConnection(EventDescriptionDao.class.getName());
        List<EventDescription> results = new ArrayList<>();

        try (PreparedStatement sqlStmt = connection.prepareStatement(sqlRequest)) {
            sqlStmt.setLong(1, size);
            sqlStmt.setLong(2, size * (page - 1));

            ResultSet resultSet = sqlStmt.executeQuery();

            while (resultSet.next()) {
                results.add(new EventDescription(
                        resultSet.getString("description"),
                        resultSet.getString("id")
                ));
            }

            return results;
        } catch (SQLException e) {
            throw new RuntimeException("EventDescriptionDao.findAllEvDescription :: ", e);
        }
    }
}
