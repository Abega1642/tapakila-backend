package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.InnerJoinQuery;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.Query;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.QueryResult;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventsCategory;
import dev.razafindratelo.tapakilaBackend.entity.EventsType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.*;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.EventMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Component
@Getter
public class EventDao implements DAO<Event> {
    private final DataSource dataSource;

    private List<Column> getColumns() {
        return List.of (
                new Column (AvailableColumn.EVENT_ID, "event_id"),
                new Column (AvailableColumn.EVENT_ORGANIZER, "event_organizer"),
                new Column (AvailableColumn.EVENT_TITLE, "event_title"),
                new Column (AvailableColumn.EVENT_DESCRIPTION, "event_description"),
                new Column (AvailableColumn.EVENT_DATE_TIME, "event_date_time"),
                new Column (AvailableColumn.EVENT_TIME_ZONE, "event_time_zone"),
                new Column (AvailableColumn.EVENT_LOCATION, "event_location"),
                new Column (AvailableColumn.EVENT_LOCATION_URL, "event_location_url"),
                new Column (AvailableColumn.EVENT_IMAGE_PATH, "event_image_path"),
                new Column (AvailableColumn.EVENT_STATUS, "event_status"),
                new Column (AvailableColumn.EVENT_CATEGORY, "event_category"),
                new Column (AvailableColumn.EVENT_NUMBER_OF_TICKET, "event_number_of_ticket"),
                new Column (AvailableColumn.EVENT_MAX_TICKET_PER_USER, "event_max_ticket_per_user"),
                new Column (AvailableColumn.CREATES_CREATED_AT, "event_created_at"),
                new Column (AvailableColumn.CREATES_UPDATED_AT, "event_updated_at"),
                new Column (AvailableColumn.USER_EMAIL, "user_email"),
                new Column (AvailableColumn.USER_LAST_NAME, "user_last_name"),
                new Column (AvailableColumn.USER_FIRST_NAME, "user_first_name"),
                new Column (AvailableColumn.USER_PROFILE_IMAGE_PATH, "user_img_profil_path"),
                new Column (AvailableColumn.USER_ROLE, "user_role"),
                new Column (AvailableColumn.USER_STATUS, "user_status")
        );
    }

    private List<InnerJoinQuery> getInnerJoinQueries() {
        return List.of (
                new InnerJoinQuery("INNER JOIN creates c ON c.id_event = e.id"),
                new InnerJoinQuery("INNER JOIN \"user\" u ON u.email = c.user_email"),
                new InnerJoinQuery("INNER JOIN has_type h ON e.id = h.id_event"),
                new InnerJoinQuery("INNER JOIN events_type ety ON ety.id = h.id_events_type"),
                new InnerJoinQuery("INNER JOIN events_category ec ON ec.event_category = e.category")
        );
    }

    private QueryResult makeQuery(List<Criteria> criteria, List<Criteria> extraCriteria) {
        List<Column> columns = new ArrayList<>(getColumns());
        String requestAsColumn =
                """
                COALESCE(
                      JSON_AGG(
                            DISTINCT JSONB_BUILD_OBJECT(
                                 'id', ety.id,
                                     'event_type', ety.event_type,
                                     'description', ety.description,
                                     'corresponding_categories',
                                     (SELECT JSONB_AGG(
                                          DISTINCT JSONB_BUILD_OBJECT(
                                               'id', ec2.id,
                                               'event_category', ec2.event_category,
                                               'description', ec2.description
                                          )
                                     )
                                     FROM events_category ec2
                                     WHERE ec2.id_event_type = ety.id)
                            )
                      ) FILTER (WHERE ety.id IS NOT NULL),
                      '[]'
                ) AS event_types
                """;
        String eventCategoryIDRequest =
                """
                (
                    SELECT ecy.id from event ev
                    INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
                )   AS event_category_id
                """;
        String eventCategoryNameRequest =
                """
               (
                   SELECT ecy.event_category from event ev
                   INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
               )   AS event_category_id
               """;
        String eventCategoryDescriptionRequest =
                """
               (
                   SELECT ecy.description from event ev
                   INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
               )   AS event_category_description
               """;

        columns.addAll(List.of(
                new Column (AvailableColumn.REQUEST_AS_COLUMN, requestAsColumn),
                new Column(AvailableColumn.REQUEST_AS_COLUMN, eventCategoryIDRequest),
                new Column(AvailableColumn.REQUEST_AS_COLUMN, eventCategoryNameRequest),
                new Column(AvailableColumn.REQUEST_AS_COLUMN,eventCategoryDescriptionRequest)
        ));
        List<Criteria> finalCriteria = new ArrayList<>(criteria);
        finalCriteria.addAll(extraCriteria);


        List<InnerJoinQuery> innerJoins = getInnerJoinQueries();
        GroupBy groupBy = new GroupBy(List.of(
                AvailableColumn.EVENT_ID,
                AvailableColumn.CREATES_CREATED_AT,
                AvailableColumn.CREATES_UPDATED_AT,
                AvailableColumn.USER_EMAIL,
                AvailableColumn.USER_LAST_NAME,
                AvailableColumn.USER_FIRST_NAME,
                AvailableColumn.USER_PROFILE_IMAGE_PATH,
                AvailableColumn.USER_ROLE,
                AvailableColumn.USER_STATUS
        ));

        Query mainQuery = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(finalCriteria)
                .groupBy(groupBy)
                .build();

        String sqlQuery = """
                WITH EventCounts AS (
                    SELECT id_event, COUNT(*) AS all_events_by_id
                    FROM has_type
                    GROUP BY id_event
                    ORDER BY id_event
                    LIMIT ? OFFSET ?
                ),
                EventTypes AS (
                    SELECT
                        e.id AS event_id,
                        ety.id AS event_type_id,
                        ety.event_type,
                        ety.description AS event_type_description,
                        ec.id AS event_category_id,
                        ec.event_category,
                        ec.description AS event_category_description
                    FROM event e
                    LEFT JOIN has_type h ON e.id = h.id_event
                    LEFT JOIN events_type ety ON ety.id = h.id_events_type
                    LEFT JOIN events_category ec ON ec.id_event_type = ety.id
                )
                """ +
                mainQuery.getSelectQuery();
        return new QueryResult(sqlQuery, mainQuery);
    }

    @Override
    public Event save(Event event) {
        Connection connection = dataSource.getConnection();
        List<Column> insertColumns = List.of(
                Column.from(AvailableColumn.EVENT_ID),
                Column.from(AvailableColumn.EVENT_ORGANIZER),
                Column.from(AvailableColumn.EVENT_TITLE),
                Column.from(AvailableColumn.EVENT_DESCRIPTION),
                Column.from(AvailableColumn.EVENT_DATE_TIME),
                Column.from(AvailableColumn.EVENT_TIME_ZONE),
                Column.from(AvailableColumn.EVENT_LOCATION),
                Column.from(AvailableColumn.EVENT_LOCATION_URL),
                Column.from(AvailableColumn.EVENT_IMAGE_PATH),
                Column.from(AvailableColumn.EVENT_CATEGORY),
                Column.from(AvailableColumn.EVENT_STATUS),
                Column.from(AvailableColumn.EVENT_NUMBER_OF_TICKET),
                Column.from(AvailableColumn.EVENT_MAX_TICKET_PER_USER)
        );
        Query queryMaker = new Query
                .Builder()
                .tableName(TableName.EVENT)
                .column(insertColumns)
                .build();
        try (PreparedStatement saveStmt = connection.prepareStatement(queryMaker.getInsertQuery().toString())) {
            saveStmt.setString(1, event.getId());
            saveStmt.setString(2, event.getOrganizer());
            saveStmt.setString(3, event.getTitle());
            saveStmt.setString(4, event.getDescription());
            saveStmt.setTimestamp(5, Timestamp.valueOf(event.getDateTime()));
            saveStmt.setString(6, event.getTimeZone().toString());
            saveStmt.setString(7, event.getLocation());
            saveStmt.setString(8, event.getLocationUrl());
            saveStmt.setString(9, event.getImagePath());
            saveStmt.setString(10, event.getCategory().getEventCategory().toString());
            saveStmt.setString(11, event.getStatus().toString());
            saveStmt.setLong(12, event.getNumberOfTickets());
            saveStmt.setInt(13, event.getMaxTicketPerUser());

            int affectedRows = saveStmt.executeUpdate();
            if (affectedRows > 0) {
                return event;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        throw new RuntimeException("Failed to save event with id " + event.getId());
    }


    @Override
    public Optional<Event> findById(String id) {
        Connection connection = dataSource.getConnection();
        List<Criteria> criteria = List.of (
                new Filter (AvailableColumn.EVENT_ID, OperatorType.EQUAL, id)
        );

        QueryResult sqlQuery = makeQuery(criteria, List.of());
        String finaLQuery = sqlQuery.sql();


        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            findStmt.setLong(1, 1);
            findStmt.setLong(2, 0);
            findStmt.setString(3, id);

            ResultSet rs = findStmt.executeQuery();

            Event event = new Event.Builder().build();
            Set<EventsType> eventTypes = new HashSet<>();

            if (rs.next()) {
                return Optional.of(new EventMapper().mapFrom(rs));
            }

            event.setEventsType(eventTypes);
            return Optional.of(event);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Event> findAll(long page, long size) {
        return findAllByCriteria(List.of(), page, size);
    }

    @Override
    public List<Event> findAllByCriteria(List<Criteria> criteria, long page, long size) {

        List<Criteria> extraCriteria = List.of(
                new Filter(AvailableColumn.EVENT_ID_REQ, OperatorType.IN, "(SELECT id_event FROM EventCounts)")
        );

        QueryResult sqlQuery = makeQuery(criteria, extraCriteria);
        String finaLQuery = sqlQuery.sql()
                + """
                   LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
                  OFFSET 0;
                  """;

        List<Event> events = new ArrayList<>();
        Connection connection = dataSource.getConnection();

        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(finaLQuery)) {

            sqlQuery.query().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, 2);

            findAllByCriteriaStmt.setLong(1, size);
            findAllByCriteriaStmt.setLong(2, size * (page - 1));


            ResultSet rs = findAllByCriteriaStmt.executeQuery();

            while (rs.next()) {
                Event ev = new EventMapper().mapFrom(rs);
                events.add(ev);
            }
            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Event update(String id, Event entity) {
        throw new NotImplementedException("Updating event not implemented yet");
    }

    @Override
    public Optional<Event> delete(String id) {
        throw new NotImplementedException("Deleting event not implemented yet");
    }
}
