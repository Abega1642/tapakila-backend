package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.InnerJoinQuery;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.Query;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.QueryResult;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.UpdateHandler;
import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OrderType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.mapper.AccountActivationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Component
public class AccountActivationDao implements DAO<AccountActivation> {
    private final DataSource dataSource;
    private final AccountActivationMapper accountActivationMapper;

    private static List<Column> getColumns() {
        return List.of(
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_ID, "account_activation_id"),
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_CODE, "account_activation_code"),
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_CREATED_AT, "account_activation_creation"),
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_EXPIRED_AT, "account_activation_expiration"),
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_ACTIVATED_AT, "account_activation_activation"),
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_IS_ACTIVE, "account_activation_is_active")
        );
    }
    private static List<InnerJoinQuery> getInnerJoinQueries() {
        return List.of(
                new InnerJoinQuery("LEFT JOIN CorrespondingUser cu ON acc.user_email = cu.user_email")
        );
    }

    private static QueryResult makeQueryResult(List<Criteria> criteria, List<Criteria> extraCriteria) {
        List<Column> allColumns = new ArrayList<>(getColumns());
        List<Criteria> finalCriteria = new ArrayList<>(criteria);
        finalCriteria.addAll(extraCriteria);

        GroupBy groupBy = new GroupBy(List.of(AvailableColumn.ACCOUNT_ACTIVATION_ID));

        String requestAsColumn =
                """
                COALESCE(
                       JSONB_OBJECT_AGG(
                              'email', cu.user_email
                       ) ||
                       JSONB_OBJECT_AGG(
                              'profileImgPath', cu.user_profile_img_path
                       ) ||
                       JSONB_OBJECT_AGG(
                               'lastName', cu.user_last_name
                       ) ||
                       JSONB_OBJECT_AGG(
                               'firstName', cu.user_first_name
                       ) ||
                       JSONB_OBJECT_AGG(
                                'userRole', cu.user_role
                       ) ||
                       JSONB_OBJECT_AGG(
                                'userStatus', cu.user_status
                       ) ||
                       JSONB_OBJECT_AGG(
                                'userCreatedAt', cu.user_created_at
                       ),
                       '{}'::jsonb
                ) AS account_activation_corresponding_user
                """;

        allColumns.add(
                new Column(AvailableColumn.REQUEST_AS_COLUMN, requestAsColumn)
        );

        Query mainQuery = new Query.Builder()
                .tableName(TableName.ACCOUNT_ACTIVATION)
                .column(allColumns)
                .criteria(finalCriteria)
                .innerJoin(getInnerJoinQueries())
                .groupBy(groupBy)
                .build();

        String sqlQuery =
                """
                WITH CorrespondingUser AS (
                      SELECT
                          u.email as user_email,
                          u.profile_img_path as user_profile_img_path,
                          u.last_name as user_last_name,
                          u.first_name AS user_first_name,
                          u.user_role AS user_role,
                          u.is_active AS user_status,
                          u.created_at AS user_created_at,
                         '[]' AS user_top_5_categories
                      FROM "user" u
                )
                """
                + mainQuery.getSelectQuery();
        return new QueryResult(sqlQuery, mainQuery);
    }

    @Override
    public AccountActivation save(AccountActivation accountActivation) {
        Connection connection = dataSource.getConnection();
        List<Column> columns = List.of(
                Column.from(AvailableColumn.ACCOUNT_ACTIVATION_ID),
                Column.from(AvailableColumn.ACCOUNT_ACTIVATION_CODE),
                Column.from(AvailableColumn.ACCOUNT_ACTIVATION_USER_EMAIL)
        );

        Query queryMaker = new Query
                .Builder()
                .tableName(TableName.ACCOUNT_ACTIVATION)
                .column(columns)
                .build();
        try (PreparedStatement saveStmt = connection.prepareStatement(queryMaker.getInsertQuery().toString())) {

            saveStmt.setString(1, accountActivation.getId());
            saveStmt.setString(2, accountActivation.getActivationCode());
            saveStmt.setString(3, accountActivation.getUser().getEmail());

            int affectedRows = saveStmt.executeUpdate();

            if (affectedRows > 0) {
                return accountActivation;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException(
                "Failed to save activation of user with email = " + accountActivation.getUser().getEmail()
        );
    }

    @Override
    public Optional<AccountActivation> findById(String id) {
        Connection connection = dataSource.getConnection();
        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.ACCOUNT_ACTIVATION_ID, OperatorType.EQUAL, id)
        );

        QueryResult sqlQuery = makeQueryResult(criteria, List.of());
        String finaLQuery = sqlQuery.sql();

        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            findStmt.setString(1, id);

            ResultSet rs = findStmt.executeQuery();

            if (rs.next()) {
                return Optional.of(accountActivationMapper.mapFrom(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<AccountActivation> findByUserEmail(String userEmail) {
        Connection connection = dataSource.getConnection();
        return findByUserEmailWithConnection(userEmail, connection);
    }

    private Optional<AccountActivation> findByUserEmailWithConnection(String email, Connection connection) {

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.ACCOUNT_ACTIVATION_USER_EMAIL, OperatorType.EQUAL, email),
                new Order(AvailableColumn.ACCOUNT_ACTIVATION_EXPIRED_AT, OrderType.DESC)
        );

        QueryResult sqlQuery = makeQueryResult(criteria, List.of());
        String finaLQuery = sqlQuery.sql()
                +  """
                    LIMIT 1 OFFSET 0;
                  """;

        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            findStmt.setString(1, email);

            ResultSet rs = findStmt.executeQuery();

            if (rs.next()) {
                return Optional.of(accountActivationMapper.mapFrom(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AccountActivation> findAll(long page, long size) {
        return findAllByCriteria(List.of(), page, size);
    }

    @Override
    public List<AccountActivation> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        Connection connection = dataSource.getConnection();
        QueryResult sqlQuery = makeQueryResult(criteria, List.of());

        String finaLQuery = sqlQuery.sql()
                + """
                    LIMIT ? OFFSET ?;
                  """;
        List<AccountActivation> accountActivations = new ArrayList<>();
        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(finaLQuery)) {
            int previousIndex = sqlQuery.query().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, 0);

            findAllByCriteriaStmt.setLong(previousIndex + 1, size);
            findAllByCriteriaStmt.setLong(previousIndex + 2, size * (page - 1));

            List<AccountActivation> activations = new ArrayList<>();
            ResultSet rs = findAllByCriteriaStmt.executeQuery();

            while (rs.next()) {
                activations.add (
                        accountActivationMapper.mapFrom(rs)
                );
            }

            return activations;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AccountActivation> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("This particular update method is not implemented");
    }

    public List<AccountActivation> update(String email, LocalDateTime activatedTime) {
        Connection connection = dataSource.getConnection();

        List<Column> activatedAt = List.of (
                new Column(AvailableColumn.ACCOUNT_ACTIVATION_ACTIVATED_AT, activatedTime.toString())
        );

        AccountActivation activationAccount = findByUserEmailWithConnection(email, connection).orElseThrow(
                () -> new ResourceNotFoundException("No activation found for email " + email)
        );

        if (!activationAccount.isActive()) {
            throw new BadRequestException("Activation code expired for user " + email);
        }

        String activationAccountId = activationAccount.getId();

        List<Filter> id = List.of (
                new Filter (AvailableColumn.ACCOUNT_ACTIVATION_ID, OperatorType.EQUAL, activationAccountId)
        );
        List<Criteria> criteria = new ArrayList<>(id);

        Query queryMaker = new Query.Builder()
                .tableName(TableName.ACCOUNT_ACTIVATION)
                .column(activatedAt)
                .criteria(criteria)
                .build();

        StringBuilder updateSQLQuery = queryMaker.getUpdateQuery(id);

        try (PreparedStatement updateStmt = connection.prepareStatement(updateSQLQuery.toString())) {

            int updatedRows = UpdateHandler.executeUpdate(activatedAt, queryMaker, updateStmt);

            if (updatedRows > 0) {
                return List.of(findById(activationAccountId).orElseThrow(
                        () -> new ResourceNotFoundException("No activation found corresponding to id = " + activationAccountId)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException("Update account activation with user email " + email + " failed.");
    }

    @Override
    public Optional<AccountActivation> delete(String id) {
        throw new NotImplementedException("Deletion not implemented");
    }
}
