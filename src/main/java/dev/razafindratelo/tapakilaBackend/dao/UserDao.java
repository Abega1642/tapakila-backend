package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.InnerJoinQuery;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.Query;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.QueryResult;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.UpdateHandler;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
public class UserDao implements DAO<User> {
    private final DataSource dataSource;

    private List<InnerJoinQuery> getInnerJoins () {
        return List.of (
                new InnerJoinQuery("LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email")
        );
    }

    private List<Column> getFindAllColumns() {
        return List.of(
                new Column (AvailableColumn.USER_EMAIL, "user_email"),
                new Column (AvailableColumn.USER_LAST_NAME, "user_last_name"),
                new Column (AvailableColumn.USER_FIRST_NAME, "user_first_name"),
                new Column (AvailableColumn.USER_PROFILE_IMAGE_PATH, "user_img_profil_path"),
                new Column (AvailableColumn.USER_ROLE, "user_role"),
                new Column (AvailableColumn.USER_STATUS, "user_status"),
                new Column (AvailableColumn.USER_CREATED_AT, "user_creation_date")
        );
    }

    private QueryResult makeQueryResult(List<Criteria> criteria, List<Criteria> extraCriteria) {
        List<Column> allColumns = new ArrayList<>(getFindAllColumns());
        List<Criteria> finalCriteria = new ArrayList<>(criteria);
        finalCriteria.addAll(extraCriteria);

        GroupBy groupBy = new GroupBy(List.of(AvailableColumn.USER_EMAIL));

        String requestAsColumn =
                """
                COALESCE(
                      JSON_AGG(
                           DISTINCT JSONB_BUILD_OBJECT(
                                'id', t5c.event_category_id,
                                'event_category', t5c.event_category,
                                'description', t5c.event_category_description
                           )
                      ) FILTER (WHERE t5c.event_category_id IS NOT NULL), '[]'
                ) AS user_top_5_categories
                """;

        allColumns.add(
                new Column(AvailableColumn.REQUEST_AS_COLUMN, requestAsColumn)
        );

        Query mainQuery = new Query.Builder()
                .tableName(TableName.USER)
                .column(allColumns)
                .criteria(finalCriteria)
                .innerJoin(getInnerJoins())
                .groupBy(groupBy)
                .build();

        String sqlQuery =
                """
                WITH Top5Categories AS (
                      SELECT
                      u.email AS user_email,
                      ec.id AS event_category_id,
                      ec.event_category,
                      ec.description AS event_category_description,
                      COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket t WHERE t.user_email = u.email), 0) AS percentage
                      FROM ticket t
                      RIGHT JOIN "user" u ON t.user_email = u.email
                      LEFT JOIN ticket_price tp ON tp.id = t.id_ticket_price
                      LEFT JOIN event e ON e.id = tp.id_event
                      LEFT JOIN events_category ec ON e.category = ec.event_category
                      GROUP BY ec.id, u.email
                      ORDER BY percentage  DESC
                      LIMIT 5
                )
                """
                + mainQuery.getSelectQuery();

        return new QueryResult(sqlQuery, mainQuery);
    }

    @Override
    public User save(User user) {
        Connection connection = dataSource.getConnection();
        List<Column> insertColumns = List.of(
                Column.from(AvailableColumn.USER_EMAIL),
                Column.from(AvailableColumn.USER_LAST_NAME),
                Column.from(AvailableColumn.USER_FIRST_NAME),
                Column.from(AvailableColumn.USER_PASSWORD),
                Column.from(AvailableColumn.USER_PROFILE_IMAGE_PATH),
                Column.from(AvailableColumn.USER_ROLE)
        );

        Query queryMaker = new Query
                .Builder()
                .tableName(TableName.USER)
                .column(insertColumns)
                .build();
        try (PreparedStatement saveStmt = connection.prepareStatement(queryMaker.getInsertQuery().toString())) {
            saveStmt.setString(1, user.getEmail());
            saveStmt.setString(2, user.getLastName());
            saveStmt.setString(3, user.getFirstName());
            saveStmt.setString(4, user.getPassword());
            saveStmt.setString(5, user.getImgProfilePath());
            saveStmt.setString(6, user.getUserRole().toString());

            int affectedRows = saveStmt.executeUpdate();

            if (affectedRows > 0) {
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException("Failed to save user with email = " + user.getEmail());
    }

    @Override
    public Optional<User> findById(String email) {
        Connection connection = dataSource.getConnection();
        return findByIdWithGivenConnection(email, connection);
    }

    private Optional<User> findByIdWithGivenConnection(String email, Connection connection) {
        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.USER_EMAIL, OperatorType.EQUAL, email)
        );
        QueryResult sqlQuery = makeQueryResult(criteria, List.of());
        String finaLQuery = sqlQuery.sql();

        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            System.out.println(findStmt);
            findStmt.setString(1, email);
            
            ResultSet rs = findStmt.executeQuery();
            
            if (rs.next()) {
                return Optional.of(new UserMapper().mapFrom(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll(long page, long size) {
        return findAllByCriteria(List.of(), page, size);
    }

    @Override
    public List<User> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        Connection connection = dataSource.getConnection();
        return findAllByCriteriaWithGivenConnection(criteria, page, size, connection);
    }
    
    private List<User> findAllByCriteriaWithGivenConnection(
            List<Criteria> criteria, long page, long size, Connection connection
    ) {

        QueryResult sqlQuery = makeQueryResult(criteria, List.of());

        String finaLQuery = sqlQuery.sql()
                + """
                    LIMIT ? OFFSET ?;
                  """;

        List<User> users = new ArrayList<>();

        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(finaLQuery)) {
            int previousIndex = sqlQuery.query().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, 0);

            findAllByCriteriaStmt.setLong(previousIndex + 1, size);
            findAllByCriteriaStmt.setLong(previousIndex + 2, size * (page - 1));

            ResultSet rs = findAllByCriteriaStmt.executeQuery();

            while (rs.next()) {
                users.add(
                        new UserMapper().mapFrom(rs)
                );
            }
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> update(List<Column> columnToBeUpdated, List<Filter> updateColumnReferences) {
        Connection connection = dataSource.getConnection();

        List<Criteria> criteria = new ArrayList<>(updateColumnReferences);
        Query queryMaker = new Query.Builder()
                .tableName(TableName.USER)
                .column(columnToBeUpdated)
                .criteria(criteria)
                .build();

        StringBuilder updateSQLQuery = queryMaker.getUpdateQuery(updateColumnReferences);

        try (PreparedStatement updateStmt = connection.prepareStatement(updateSQLQuery.toString())) {

            int updatedRows = UpdateHandler.executeUpdate(columnToBeUpdated, queryMaker, updateStmt);

            if (updatedRows > 0) {
                return findAllByCriteriaWithGivenConnection(criteria, 1, updatedRows, connection);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException("Error while updating user(s)");
    }

    @Override
    public Optional<User> delete(String email) {
        List<Column> column = List.of (
                Column.from(AvailableColumn.USER_STATUS)
        );

        List<Filter> filters = List.of(
                new Filter(AvailableColumn.USER_EMAIL, OperatorType.EQUAL, email)
        );

        return Optional.of(update(column, filters).getFirst());
    }
}
