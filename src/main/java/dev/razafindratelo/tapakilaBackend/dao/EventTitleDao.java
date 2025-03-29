package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dto.EventTitle;
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
public class EventTitleDao {
    private final DataSource dataSource;

    public List<EventTitle> findAllEvTitle(long page, long size) {
        String sqlRequest =
                """
                SELECT title, id FROM event LIMIT ? OFFSET ?
                """;
        Connection connection = dataSource.getConnection(EventTitleDao.class.getName());

        List<EventTitle> titles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setLong(1, size);
            statement.setLong(2, size * (page - 1));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                titles.add(new EventTitle(
                        resultSet.getString("title"),
                        resultSet.getString("id"))
                );
            }

            return titles;
        } catch (SQLException e) {
            throw new RuntimeException("EventTitleDao.findAllEvTitile" + e);
        }
    }
}
