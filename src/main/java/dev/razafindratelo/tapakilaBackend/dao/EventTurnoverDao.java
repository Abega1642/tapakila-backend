package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.EventTurnover;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.mapper.EventTurnoverMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class EventTurnoverDao {
    private final DataSource dataSource;
    private final EventTurnoverMapper mapper;

    public EventTurnover findTurnoverByEventId(String eventId) {
        final LocalDate DEFAULT_DATE = LocalDate.now();
        return findTurnoverByEventIdAtGivenDate(eventId, DEFAULT_DATE);
    }

    public EventTurnover findTurnoverByEventIdAtGivenDate(String eventId, LocalDate date) {
        Connection connection = dataSource.getConnection(EventTurnoverDao.class.getName());
        String sqlQuery =
                """
                        WITH TicketType AS (
                            SELECT
                                tt.id AS ticket_type_id,
                                tt.ticket_type AS ticket_type,
                                tt.img_path AS ticket_type_img_path,
                                tt.description AS ticket_type_description
                            FROM tickets_type tt
                        ),
                             TicketSales AS (
                                 SELECT
                                     tp.id AS ticket_price_id,
                                     tp.price AS ticket_price,
                                     tp.currency AS ticket_price_currency,
                                     tp.created_at AS ticket_price_created_at,
                                     tp.max_number AS ticket_price_max_number,
                                     tp.id_event AS associated_event_id,
                                     tp.id_ticket_type,
                                     COUNT(t.id) AS ticket_count
                                 FROM ticket t
                                          JOIN ticket_price tp ON t.id_ticket_price = tp.id
                                 WHERE EXTRACT(YEAR FROM t.purchased_at) = ?
                                   AND EXTRACT(MONTH FROM t.purchased_at) = ?
                                 GROUP BY tp.id, tp.price, tp.currency, tp.created_at, tp.max_number, tp.id_event, tp.id_ticket_type
                             )
                        SELECT
                            ts.associated_event_id AS event_id,
                            JSONB_AGG(
                                    JSONB_BUILD_OBJECT(
                                            'ticketInfo', JSONB_BUILD_OBJECT(
                                            'id', ts.ticket_price_id,
                                            'price', ts.ticket_price,
                                            'currency', ts.ticket_price_currency,
                                            'createdAt', ts.ticket_price_created_at,
                                            'maxNumber', ts.ticket_price_max_number,
                                            'associatedEventId', ts.associated_event_id,
                                            'leftTickets', get_event_left_ticket_of_given_ticket_type_at_a_given_date(ts.associated_event_id, tkt.ticket_type, ?::date, ?::date),
                                            'correspondingTicketType', JSONB_BUILD_OBJECT(
                                                    'id', tkt.ticket_type_id,
                                                    'ticketType', tkt.ticket_type,
                                                    'imgPath', tkt.ticket_type_img_path,
                                                    'description', tkt.ticket_type_description
                                                                       )
                                                          ),
                                            'turnover', ts.ticket_price * ts.ticket_count
                                    )
                            ) AS tickets,
                            SUM(ts.ticket_price * ts.ticket_count) AS total_turnover
                        FROM TicketSales ts
                                 LEFT JOIN TicketType tkt ON tkt.ticket_type_id = ts.id_ticket_type
                        WHERE ts.associated_event_id = ?
                        GROUP BY ts.associated_event_id;
                """;

        try (PreparedStatement findStmt = connection.prepareStatement(sqlQuery)) {
            findStmt.setInt(1, date.getYear());
            findStmt.setInt(2, date.getMonthValue());
            findStmt.setDate(3, null);
            findStmt.setDate(4, Date.valueOf(date));
            findStmt.setString(5, eventId);

            ResultSet rs = findStmt.executeQuery();
            if (rs.next())
                return mapper.mapFrom(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        throw new ResourceNotFoundException("Event turnover not found");
    }

    public List<EventTurnover> findAll(long page, long size) {
        final LocalDate DEFAULT_DATE = LocalDate.now();
        return findAllAtAGivenDate(DEFAULT_DATE, page, size);
    }

    public List<EventTurnover> findAllAtAGivenDate(LocalDate date, long page, long size) {
        Connection connection = dataSource.getConnection(EventTurnoverDao.class.getName());
        List<EventTurnover> turnovers = new ArrayList<>();
        String sqlQuery =
                """
                        WITH TicketType AS (
                            SELECT
                                tt.id AS ticket_type_id,
                                tt.ticket_type AS ticket_type,
                                tt.img_path AS ticket_type_img_path,
                                tt.description AS ticket_type_description
                            FROM tickets_type tt
                        ),
                             TicketSales AS (
                                 SELECT
                                     tp.id AS ticket_price_id,
                                     tp.price AS ticket_price,
                                     tp.currency AS ticket_price_currency,
                                     tp.created_at AS ticket_price_created_at,
                                     tp.max_number AS ticket_price_max_number,
                                     tp.id_event AS associated_event_id,
                                     tp.id_ticket_type,
                                     COUNT(t.id) AS ticket_count
                                 FROM ticket t
                                          JOIN ticket_price tp ON t.id_ticket_price = tp.id
                                 WHERE EXTRACT(YEAR FROM t.purchased_at) = ?
                                   AND EXTRACT(MONTH FROM t.purchased_at) = ?
                                 GROUP BY tp.id, tp.price, tp.currency, tp.created_at, tp.max_number, tp.id_event, tp.id_ticket_type
                             )
                        SELECT
                            ts.associated_event_id AS event_id,
                            JSONB_AGG(
                                    JSONB_BUILD_OBJECT(
                                            'ticketInfo', JSONB_BUILD_OBJECT(
                                            'id', ts.ticket_price_id,
                                            'price', ts.ticket_price,
                                            'currency', ts.ticket_price_currency,
                                            'createdAt', ts.ticket_price_created_at,
                                            'maxNumber', ts.ticket_price_max_number,
                                            'associatedEventId', ts.associated_event_id,
                                            'leftTickets', get_event_left_ticket_of_given_ticket_type_at_a_given_date(ts.associated_event_id, tkt.ticket_type, ?::date, ?::date),
                                            'correspondingTicketType', JSONB_BUILD_OBJECT(
                                                    'id', tkt.ticket_type_id,
                                                    'ticketType', tkt.ticket_type,
                                                    'imgPath', tkt.ticket_type_img_path,
                                                    'description', tkt.ticket_type_description
                                                                       )
                                                          ),
                                            'turnover', ts.ticket_price * ts.ticket_count
                                    )
                            ) AS tickets,
                            SUM(ts.ticket_price * ts.ticket_count) AS total_turnover
                        FROM TicketSales ts
                        LEFT JOIN TicketType tkt ON tkt.ticket_type_id = ts.id_ticket_type
                        GROUP BY ts.associated_event_id
                        ORDER BY ts.associated_event_id
                        LIMIT ? OFFSET ?;
                """;
        try (PreparedStatement findStmt = connection.prepareStatement(sqlQuery)) {
            findStmt.setInt(1, date.getYear());
            findStmt.setInt(2, date.getMonthValue());
            findStmt.setDate(3, null);
            findStmt.setDate(4, Date.valueOf(date));
            findStmt.setLong(5, size);
            findStmt.setLong(6, size * (page - 1));

            ResultSet rs = findStmt.executeQuery();
            while (rs.next()) {
                EventTurnover et = mapper.mapFrom(rs);
                turnovers.add(et);
            }

            return turnovers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
