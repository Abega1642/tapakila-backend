package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.InnerJoinQuery;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.Query;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.QueryResult;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.PaymentMode;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.GroupBy;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.EventTypeDetailMapper;
import lombok.AllArgsConstructor;
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
public class EventTypeDetailDao implements DAO<EventTypeDetail> {
    private final DataSource dataSource;

    private List<InnerJoinQuery> getInnerJoinQueries() {
        return List.of (
                new InnerJoinQuery("LEFT JOIN EventCategories ec ON ety.id = ec.event_type_id")
        );
    }

    private List<Column> getColumns() {
        return List.of (
                new Column(AvailableColumn.EVENT_TYPE_ID, "event_type_id"),
                new Column(AvailableColumn.EVENT_TYPE__, "event_type"),
                new Column(AvailableColumn.EVENT_TYPE_DESCRIPTION, "event_type_description")
        );
    }


    private QueryResult makeQuery(List<Criteria> criteria, List<Criteria> extraCriteria) {
        List<Column> columns = new ArrayList<>(getColumns());
        String requestAsColumn =
                """
                COALESCE(
                      JSON_AGG(
                            DISTINCT JSONB_BUILD_OBJECT(
                                  'id', ec.event_type_id,
                                  'eventCategory', ec.event_category,
                                  'description', ec.event_category_description
                            )
                      ) FILTER (WHERE ec.event_type_id IS NOT NULL),
                      '[]'
                ) AS corresponding_categories
                """;

        columns.add(new Column (AvailableColumn.REQUEST_AS_COLUMN, requestAsColumn));
        List<Criteria> finalCriteria = new ArrayList<>(criteria);
        finalCriteria.addAll(extraCriteria);


        List<InnerJoinQuery> innerJoins = getInnerJoinQueries();
        GroupBy groupBy = new GroupBy(List.of(
                AvailableColumn.EVENT_TYPE_ID
        ));

        Query mainQuery = new Query.Builder()
                .tableName(TableName.EVENTS_TYPE)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(finalCriteria)
                .groupBy(groupBy)
                .build();

        String sqlQuery = """
                WITH EventTypeCounts AS (
                     SELECT id_event_type, COUNT(*) AS all_events_type_by_id
                     FROM events_category
                     GROUP BY id_event_type
                     ORDER BY id_event_type
                     LIMIT ? OFFSET ?
                 ),
                 EventCategories AS (
                     SELECT
                         ety.id AS event_type_id,
                         ec.id AS event_category_id,
                         ec.event_category,
                         ec.description AS event_category_description
                     FROM events_type ety
                     LEFT JOIN events_category ec ON ec.id_event_type = ety.id
                 )
                """ +
                mainQuery.getSelectQuery();
        return new QueryResult(sqlQuery, mainQuery);
    }

    @Override
    public EventTypeDetail save(EventTypeDetail eventTypeDetail) {
        Connection connection = dataSource.getConnection(EventTypeDetailDao.class.getName());
        List<Column> insertColumns = List.of(
                Column.from(AvailableColumn.EVENT_TYPE_ID),
                Column.from(AvailableColumn.EVENT_TYPE__),
                Column.from(AvailableColumn.EVENT_TYPE_DESCRIPTION)
        );
        Query queryMaker = new Query
                .Builder()
                .tableName(TableName.EVENTS_TYPE)
                .column(insertColumns)
                .build();
        try (
                PreparedStatement createNewEventTypeEnumValueStmt = connection.prepareStatement(
                        """
                            SELECT * FROM events_type;
                            """
                );
                PreparedStatement saveStmt = connection.prepareStatement(queryMaker.getInsertQuery().toString())
        ) {
            saveStmt.setString(1, eventTypeDetail.getId());
            saveStmt.setString(2, eventTypeDetail.getEventType().toString());
            saveStmt.setString(3, eventTypeDetail.getDescription());

            ///                                     **Note:**

            ///      Notice that I named a {@code createNewEventTypeEnumValueStmt} in the try-catch with resources,
            ///      and you should also notice that I just put {@code SELECT * FROM events_type} as its value.
            ///     This is because, we didn't decide if USER.ADMIN can just add a new type of event.

            ///     I think it will be better that only developers can add new types related to SQL enum type.

            throw new NotImplementedException("This method EventTypeDetailDao.save is not implemented");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean saveEventTypeWithGivenConnection(Connection connection, String eventTitle, String eventLocationUrl, EventType eventType) {
        String sql =
                """
                        INSERT INTO has_type (id_event, id_events_type) VALUES
                    (
                        (SELECT id FROM "event" WHERE title = ? AND location_url = ?),
                        (SELECT id FROM events_type WHERE event_type = (?::event_type))
                    );
                """;
        try (PreparedStatement saveTypeStmt = connection.prepareStatement(sql)) {
            saveTypeStmt.setString(1, eventTitle);
            saveTypeStmt.setString(2, eventLocationUrl);
            saveTypeStmt.setString(3, eventType.toString());

            if (saveTypeStmt.executeUpdate() > 0)
                return true;

            throw new SQLException("EventTypeDetailDao.saveEventTypeWithGivenConnection :: could not save event type");

        } catch (SQLException e) {
            throw new RuntimeException("EventTypeDetailDao.saveEventTypeWithGivenConnection :: " + e.getMessage());
        }
    }

    @Override
    public Optional<EventTypeDetail> findById(String id) {
        List<Criteria> extraCriteria = List.of(
                new Filter(AvailableColumn.EVENT_TYPE_ID, OperatorType.EQUAL, id)
        );

        QueryResult sqlQuery = makeQuery(List.of(), extraCriteria);
        String finaLQuery = sqlQuery.sql();

        Connection connection = dataSource.getConnection(EventTypeDetailDao.class.getName());

        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            findStmt.setLong(1, 1);
            findStmt.setLong(2, 0);
            findStmt.setString(3, id);

            ResultSet rs = findStmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new EventTypeDetailMapper().mapFrom(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<EventTypeDetail> findAll(long page, long size) {
        return findAllByCriteria(List.of(), page, size);
    }

    @Override
    public List<EventTypeDetail> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        List<Criteria> extraCriteria = List.of(
                new Filter(AvailableColumn.EVENT_TYPE_ID_REQ, OperatorType.IN, "(SELECT id_event_type FROM EventTypeCounts)")
        );

        QueryResult sqlQuery = makeQuery(criteria, extraCriteria);
        String finaLQuery = sqlQuery.sql()
                + """
                   LIMIT (SELECT COALESCE(SUM(all_events_type_by_id), 0) FROM EventTypeCounts)
                  OFFSET 0;
                  """;

        List<EventTypeDetail> eventTypes = new ArrayList<>();
        Connection connection = dataSource.getConnection(EventTypeDetailDao.class.getName());

        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(finaLQuery)) {

            sqlQuery.query().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, 2);

            findAllByCriteriaStmt.setLong(1, size);
            findAllByCriteriaStmt.setLong(2, size * (page - 1));


            ResultSet rs = findAllByCriteriaStmt.executeQuery();
            while(rs.next()) {
                EventTypeDetail ev = new EventTypeDetailMapper().mapFrom(rs);
                eventTypes.add(ev);
            }
            return eventTypes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EventTypeDetail> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("EventTypeDetail update not implemented yet");
    }

    @Override
    public Optional<EventTypeDetail> delete(String id) {
        throw new NotImplementedException("EventTypeDetail delete not implemented yet");
    }
}
