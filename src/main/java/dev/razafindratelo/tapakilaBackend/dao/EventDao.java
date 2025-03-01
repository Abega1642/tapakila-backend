package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.dao.queryfactory.InnerJoinQuery;
import dev.razafindratelo.tapakilaBackend.dao.queryfactory.Query;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventsType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.EventMapper;
import dev.razafindratelo.tapakilaBackend.mapper.EventsTypeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
@Component
@Getter
public class EventDao implements DAO<Event> {
    private final DataSource dataSource;

    @Override
    public Event save(Event entity) {
        throw new NotImplementedException("Saving event not implemented yet");
    }

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
                new Column (AvailableColumn.USER_ROLE, "user_role"),
                new Column (AvailableColumn.USER_STATUS, "user_status"),
                new Column (AvailableColumn.EVENT_TYPE_ID, "event_type_id"),
                new Column (AvailableColumn.EVENT_TYPE__, "event_type"),
                new Column (AvailableColumn.EVENT_TYPE_DESCRIPTION, "event_type_description"),
                new Column (AvailableColumn.EVENT_CATEGORY_ID, "event_category_id"),
                new Column (AvailableColumn.EVENT_CATEGORY_DESCRIPTION, "event_category_description")
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

    @Override
    public Optional<Event> findById(String id) {
        Connection connection = dataSource.getConnection();

        List<Column> columns = getColumns();
        List<InnerJoinQuery> innerJoins = getInnerJoinQueries();

        Query query = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(List.of (
                        new Filter(AvailableColumn.EVENT_ID, OperatorType.EQUAL, id, ValueType.STRING)
                ))
                .build();

        try (PreparedStatement findStmt = connection.prepareStatement(query.getSelectQuery().toString())) {
            findStmt.setString(1, id);

            ResultSet rs = findStmt.executeQuery();

            Event event = new Event.Builder().build();
            Set<EventsType> eventTypes = new HashSet<>();

            while (rs.next()) {
                Event e = new EventMapper().mapFrom(rs);
                if (!e.equals(event)) {
                    event = e;
                }
                EventsType ety = new EventsTypeMapper().mapFrom(rs);
                eventTypes.add(ety);
            }

            event.setEventsType(eventTypes);
            return Optional.of(event);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Event> findAll(long page, long size) {
        throw new NotImplementedException("Finding all events not implemented yet");
    }

    @Override
    public List<Event> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        List<Column> columns = getColumns();
        List<InnerJoinQuery> innerJoins = getInnerJoinQueries();

        Query query = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoins)
                .criteria(criteria)
                .build();

        List<Event> events = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (PreparedStatement findAllByCriteriaStmt = connection.prepareStatement(
                query.getSelectQuery()
                        .append(" LIMIT ? OFFSET ?")
                        .toString()
        )) {
            int paramIndex = query.completeQueryAndReturnLastParamIndex(findAllByCriteriaStmt);
            findAllByCriteriaStmt.setLong(paramIndex + 1, size);
            findAllByCriteriaStmt.setLong(paramIndex + 2, size * (page - 1));

            ResultSet rs = findAllByCriteriaStmt.executeQuery();
            Map<String, Event> evs = new HashMap<>();
            while (rs.next()) {
                Event ev = new EventMapper().mapFrom(rs);

                Event e = evs.get(ev.getId());

                if (e == null) {
                    evs.put(ev.getId(), ev);
                    e = evs.get(ev.getId());

                    e.setEventsType(new HashSet<>());
                    e.getEventsType().add(new EventsTypeMapper().mapFrom(rs));
                    events.add(ev);

                } else {
                    e.getEventsType().add(new EventsTypeMapper().mapFrom(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return events;
    }

    public static void main(String[] args) {
        var d = new EventDao(new DataSource());

        d.findAllByCriteria(List.of(
        ), 2, 2).forEach(System.out::println);
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
