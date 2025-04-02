package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.*;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.*;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.EventMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Component
@Getter
public class EventDao implements DAO<Event> {
    private final DataSource dataSource;
	private final EventMapper eventMapper;

    /**
     * {@code getColumns} method is a methods that handle the list of most / frequently used columns in this {@link EventDao}
     * @return: the list of the most / frequently used columns
     */
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
                new Column (AvailableColumn.USER_PASSWORD, "user_password"),
                new Column (AvailableColumn.USER_STATUS, "user_status"),
                new Column (AvailableColumn.USER_CREATED_AT, "user_creation_date")
        );
    }

    private List<InnerJoinQuery> getInnerJoinQueries() {
        return List.of (
                new InnerJoinQuery("INNER JOIN creates c ON c.id_event = e.id"),
                new InnerJoinQuery("INNER JOIN \"user\" u ON u.email = c.user_email"),
                new InnerJoinQuery("LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email"),
                new InnerJoinQuery("LEFT JOIN EventTypes ety ON e.id = ety.event_id"),
                new InnerJoinQuery("LEFT JOIN events_category ec ON ec.event_category = e.category AND ec.id_event_type = ety.event_type_id"),
                new InnerJoinQuery("LEFT JOIN ticket_price tp ON tp.id_event = e.id"),
                new InnerJoinQuery("LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type")
        );
    }

    /**
     * {@code makeQuery} is a method that provides the {@link StringBuilder} object named {@code sqlQuery} and a
     * {@link Query} object which is going to be frequently used in this current class {@link EventDao}
     * @param criteria: The criteria related to the parent method to make the SQL request.
     * @param extraCriteria : some extra criteria
     * @return: a {@link QueryResult} object that contains the SQL query {@code sqlQuery} and a {@link Query} object
     */
    private ExtraQueryResult makeQuery(List<Criteria> criteria, List<Criteria> extraCriteria) {
        List<Column> columns = new ArrayList<>(getColumns());
        String requestAsColumn =
                """
                COALESCE(
                      JSON_AGG(
                            DISTINCT JSONB_BUILD_OBJECT(
                                 'id', t5c.event_category_id,
                                 'eventCategory', t5c.event_category,
                                 'description', t5c.event_category_description
                            )
                      ) FILTER (WHERE t5c.event_category_id IS NOT NULL),
                                            '[]'
                ) AS user_top_5_categories,
                COALESCE(
                      JSON_AGG(
                            DISTINCT JSONB_BUILD_OBJECT(
                                 'id', ety.event_type_id,
                                 'eventType', ety.event_type,
                                 'description', ety.event_type_description,
                                 'correspondingCategories',
                                 (
                                    SELECT JSONB_AGG(
                                          DISTINCT JSONB_BUILD_OBJECT(
                                               'id', ec2.id,
                                               'eventCategory', ec2.event_category,
                                               'description', ec2.description
                                          )
                                 )
                                 FROM events_category ec2
                                 WHERE ec2.id_event_type = ety.event_type_id)
                            )
                      ) FILTER (WHERE ety.event_type_id IS NOT NULL),
                      '[]'
                ) AS event_types,
                COALESCE(
                      JSON_AGG(
                           DISTINCT JSONB_BUILD_OBJECT(
                               'id', tp.id,
                               'price', tp.price,
                               'currency', tp.currency,
                               'createdAt', tp.created_at,
                               'maxNumber', tp.max_number,
                               'associatedEventId', tp.id_event,
                               'correspondingTicketType',
                               JSONB_BUILD_OBJECT(
                                      'id', tkt.ticket_type_id,
                                      'ticketType', tkt.ticket_type,
                                      'imgPath', tkt.ticket_type_img_path,
                                      'description', tkt.ticket_type_description
                               ),
                              'leftTickets', get_event_left_ticket_of_given_ticket_type_at_a_given_date(tp.id_event, tkt.ticket_type, ?, ?)
                           )
                      ) FILTER (WHERE tp.id IS NOT NULL), '[]'
                ) AS event_left_tickets
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
                AvailableColumn.USER_STATUS,
                AvailableColumn.EVENT_DATE_TIME,
                AvailableColumn.USER_CREATED_AT
        ));

        Query mainQuery = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(finalCriteria)
                .groupBy(groupBy)
                .build();

        Query headerQuery = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(criteria)
                .groupBy(groupBy)
                .build();

        String sqlQuery = """
                WITH EventCounts AS (
                    SELECT hty.id_event, COUNT(*) AS all_events_by_id
                    FROM has_type hty
                    LEFT JOIN event e ON e.id = hty.id_event 
                """ +
                headerQuery.getWhereClause() +
                """
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
                ),
                Top5Categories AS (
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
                ),
                TicketType AS (
                    SELECT
                        tt.id AS ticket_type_id,
                        tt.ticket_type AS ticket_type,
                        tt.img_path AS ticket_type_img_path,
                        tt.description AS ticket_type_description
                    FROM tickets_type tt
                )
                """ +
                mainQuery.getSelectQuery();
		
        return new ExtraQueryResult(sqlQuery, headerQuery, mainQuery);
    }

    public List<Event> findAllBetweenDates(LocalDate from, LocalDate to) {
        Connection connection = dataSource.getConnection(EventDao.class.getName());
        ExtraQueryResult sqlQuery = makeQuery(List.of(), List.of());

        String finalQuery = sqlQuery.sql().substring(0, 134) + sqlQuery.sql().substring(155);

        try (PreparedStatement findAllStmt = connection.prepareStatement(finalQuery)) {

            findAllStmt.setDate(1, Date.valueOf(from));
            findAllStmt.setDate(2, Date.valueOf(to));

            ResultSet rs = findAllStmt.executeQuery();
            List<Event> events = new ArrayList<>();

            while (rs.next()) {
                events.add(eventMapper.mapFrom(rs));
            }
            return events;

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("EventDao.findAllBetweenDates :: %s", e.getMessage())
            );
        }
    }


    @Override
    public Event save(Event event) {
        Connection connection = dataSource.getConnection(EventDao.class.getName());
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

            boolean isCreatorSaved = UserDao.saveWhoCreatedEventWithGivenConnection(
                    connection,
                    event.getCreatedBy().getEmail(),
                    event.getTitle(),
                    event.getLocationUrl()
            );

            List<Boolean> areTheTypeSaved = new ArrayList<>();
            event.getEventTypeDetail().forEach(t -> areTheTypeSaved.add(
                    EventTypeDetailDao.saveEventTypeWithGivenConnection(
                            connection,
                            event.getTitle(),
                            event.getLocationUrl(),
                            t.getEventType()
                    )
            ));

            if (affectedRows > 0 && isCreatorSaved && !areTheTypeSaved.contains(Boolean.FALSE))
                return event;

            throw new SQLException(
                    "EventDao.save :: error while saving event. Might caused by the event attributes or the user creator, or the type of the event"
            );
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("EventDao.save :: %s", e.getMessage())
            );
        }
    }

    public Optional<Event> findByIdWIthGivenConnection(Connection connection, String id) {
        final LocalDate DEFAULT_DATE = LocalDate.now();


        List<Criteria> criteria = List.of (
                new Filter (AvailableColumn.EVENT_ID, OperatorType.EQUAL, id)
        );

        ExtraQueryResult sqlQuery = makeQuery(criteria, List.of());
        String finaLQuery = sqlQuery.sql();


        try (PreparedStatement findStmt = connection.prepareStatement(finaLQuery)) {
            findStmt.setString(1, id);
            findStmt.setLong(2, 1);
            findStmt.setLong(3, 0);
            findStmt.setDate(4, Date.valueOf(DEFAULT_DATE));
            findStmt.setDate(5, null);
            findStmt.setString(6, id);

            ResultSet rs = findStmt.executeQuery();

            Event event = Event.builder().build();
            Set<EventTypeDetail> eventTypes = new HashSet<>();

            if (rs.next()) {
                return Optional.of(eventMapper.mapFrom(rs));
            }

            event.setEventTypeDetail(eventTypes);
            return Optional.of(event);

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("EventDao.findById :: %s", e.getMessage())
            );
        }
    }

    @Override
    public Optional<Event> findById(String id) {
        Connection connection = dataSource.getConnection(EventDao.class.getName());
        return findByIdWIthGivenConnection(connection, id);
    }

    @Override
    public List<Event> findAll(long page, long size) {
        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.EVENT_STATUS, OperatorType.EQUAL, EventStatus.PUBLISHED.toString())
        );
        return findAllByCriteria(criteria, page, size);
    }

    public List<Event> findAllWithAGivenTicketDateInterval(LocalDate from, LocalDate to, long page, long size) {
        return findAllByCriteriaWithTicketDateInterval(List.of(), from, to, page, size);
    }

    @Override
    public List<Event> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        return findAllByCriteriaWithGivenConnection(criteria, page, size, dataSource.getConnection(EventDao.class.getName()));
    }

    public List<Event> findAllByCriteriaWithTicketDateInterval
            (List<Criteria> criteria, LocalDate ticketDateFrom, LocalDate ticketDateTo, long page, long size) {

        return findAllByCriteriaWithAGivenTicketDateIntervalWithGivenConnection(
                dataSource.getConnection(EventDao.class.getName()),
                criteria,
                ticketDateFrom,
                ticketDateTo,
                page,
                size
        );
    }

    /**
     * This method {@code findAllByCriteriaWithGivenConnection} provides the advantage by allowing the use of{@code findAllByCriteria}
     * as many times as we want in different Entity DAO by using only the current {@code Connection} of the method
     * @param criteria : filter criteria or some other criteria we want to apply to our data
     * @param page : page where we want our data to be read
     * @param size : returned resources size in that specific {@code page}
     * @param connection : connection of the parent method that use this method
     * @return : list of events corresponding to the given {@code criteria}
     */
    private List<Event> findAllByCriteriaWithGivenConnection(List<Criteria> criteria, long page, long size, Connection connection) {
        final LocalDate DEFAULT_DATE = LocalDate.now();

        return findAllByCriteriaWithAGivenTicketDateIntervalWithGivenConnection(
                connection,
                criteria,
                null,
                DEFAULT_DATE,
                page,
                size
        );
    }

    /**
     * This method {@code findAllByCriteriaWithAGivenTicketDateWithGivenConnection} provides the advantage by allowing
     * the use of{@code findAllByCriteriaWithTicketDate}
     * as many times as we want in different Entity DAO by using only the current {@code Connection} of the method
     * @param criteria : filter criteria or some other criteria we want to apply to our data
     * @param ticketDateFrom : indicates the start date of the tickets states
     * @param ticketDateTo : indicates the end date of the tickets states
     * @param page : page where we want our data to be read
     * @param size : returned resources size in that specific {@code page}
     * @param connection : connection of the parent method that use this method
     * @return : list of events corresponding to the given {@code criteria}
     */
    private List<Event> findAllByCriteriaWithAGivenTicketDateIntervalWithGivenConnection
            (Connection connection, List<Criteria> criteria, LocalDate ticketDateFrom, LocalDate ticketDateTo, long page,long size) {
        List<Criteria> extraCriteria = List.of(
                new Filter(AvailableColumn.EVENT_ID_REQ, OperatorType.IN, "(SELECT id_event FROM EventCounts)")
        );
        List<Criteria> finalCriteria = new ArrayList<>(criteria);

        finalCriteria.add(
                new Order(AvailableColumn.EVENT_DATE_TIME, OrderType.DESC)
        );

        ExtraQueryResult sqlQuery = makeQuery(finalCriteria, extraCriteria);

        String finaLQuery = sqlQuery.sql()
                + """
                   LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
                  OFFSET 0;
                  """;

        List<Event> events = new ArrayList<>();

        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(finaLQuery)) {

            int lastParam = sqlQuery.header().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, 0);
            sqlQuery.main().completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt, lastParam + 4);

            findAllByCriteriaStmt.setLong(lastParam + 1, size);
            findAllByCriteriaStmt.setLong(lastParam + 2, size * (page - 1));
		
            if (ticketDateFrom == null) {
                findAllByCriteriaStmt.setDate(lastParam + 3, null);
            } else {
                findAllByCriteriaStmt.setDate(lastParam + 3, Date.valueOf(ticketDateFrom));
            }

            findAllByCriteriaStmt.setDate(lastParam + 4, Date.valueOf(ticketDateTo));

			
            ResultSet rs = findAllByCriteriaStmt.executeQuery();

            while (rs.next()) {
                Event ev = eventMapper.mapFrom(rs);
                events.add(ev);
            }
            return events;

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format(
                            "EventDao.findAllByCriteriaWithAGivenTicketDateIntervalWithGivenConnection :: %s",
                            e.getMessage()
                    )
            );
        }
    }

    @Override
    public List<Event> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        Connection connection = dataSource.getConnection(EventDao.class.getName());

        List<Criteria> criteria = new ArrayList<>(updateColumnReferences);
        Query queryMaker = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columnsToBeUpdated)
                .criteria(criteria)
                .build();

        StringBuilder updateQuery = queryMaker.getUpdateQuery(updateColumnReferences);

        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery.toString())) {

            int updatedRows = UpdateHandler.executeUpdate(columnsToBeUpdated, queryMaker, updateStmt);

            if (updatedRows != 0) {
                return findAllByCriteriaWithGivenConnection(criteria, 1, updatedRows, connection);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("EventDao.update :: %s", e.getMessage())
            );
        }

        throw new RuntimeException("EventDao.update :: Error while updating event(s)");
    }

    @Override
    public Optional<Event> delete(String id) {
        String sql =
                """
                UPDATE event SET status = 'CANCELED' WHERE id = ?
                """;
        Connection connection = dataSource.getConnection(EventDao.class.getName());

        try (PreparedStatement deletionStmt = connection.prepareStatement(sql)) {
            deletionStmt.setString(1, id);

            if (deletionStmt.executeUpdate() > 0)
                return findByIdWIthGivenConnection(connection, id);

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "EventDao.delete :: Error while deleting event"
            );
        }
    }
}
