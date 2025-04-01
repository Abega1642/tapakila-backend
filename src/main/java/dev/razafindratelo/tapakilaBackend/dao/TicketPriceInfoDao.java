package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.TicketPriceInfoMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
public class TicketPriceInfoDao {
    private final DataSource dataSource;
    private final TicketPriceInfoMapper ticketPriceInfoMapper;

    public List<TicketPriceInfo> findAllByEventIdAtAGivenDate(String eventId, LocalDate at) {
        String sql =
                """
                        WITH TicketType AS (
                            SELECT
                                tt.id AS ticket_type_id,
                                tt.ticket_type AS ticket_type,
                                tt.img_path AS ticket_type_img_path,
                                tt.description AS ticket_type_description
                            FROM tickets_type tt
                        )
                        SELECT
                            tp.id AS ticket_price_id,
                            tp.price AS ticket_price,
                            tp.currency AS ticket_price_currency,
                            tp.created_at AS ticket_price_created_at,
                            tp.max_number AS ticket_price_max_number,
                            tp.id_event as associated_event_id,
                            JSONB_BUILD_OBJECT(
                                    'id', tkt.ticket_type_id,
                                    'ticketType', tkt.ticket_type,
                                    'imgPath', tkt.ticket_type_img_path,
                                    'description', tkt.ticket_type_description
                            ) as corresponding_ticket_type,
                            get_event_left_ticket_of_given_ticket_type_at_a_given_date(tp.id_event, tkt.ticket_type, null, (?::date)) as left_tickets
                        FROM ticket_price tp
                        LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type
                        WHERE tp.id_event = ?;
                """;

        Connection connection = dataSource.getConnection(TicketPriceInfoDao.class.getName());
        List<TicketPriceInfo> ticketPriceInfos = new ArrayList<>();

        try (PreparedStatement findStmt = connection.prepareStatement(sql)) {
            findStmt.setDate(1, Date.valueOf(at));
            findStmt.setString(2, eventId);

            ResultSet rs = findStmt.executeQuery();

            while (rs.next()) {
                ticketPriceInfos.add(
                    ticketPriceInfoMapper.mapFrom(rs)
                );
            }

            return ticketPriceInfos;
        } catch (SQLException e) {
            throw new RuntimeException("TicketPriceInfoDao.findAllByEventIdAtAGivenDate :: " +e);
        }
    }


    public TicketPriceInfo findTicketPriceInfoById(String ticketId, LocalDate at) {
        String sql =
                """
                        WITH TicketType AS (
                            SELECT
                                tt.id AS ticket_type_id,
                                tt.ticket_type AS ticket_type,
                                tt.img_path AS ticket_type_img_path,
                                tt.description AS ticket_type_description
                            FROM tickets_type tt
                        )
                        SELECT
                            tp.id AS ticket_price_id,
                            tp.price AS ticket_price,
                            tp.currency AS ticket_price_currency,
                            tp.created_at AS ticket_price_created_at,
                            tp.max_number AS ticket_price_max_number,
                            tp.id_event as associated_event_id,
                            JSONB_BUILD_OBJECT(
                                    'id', tkt.ticket_type_id,
                                    'ticketType', tkt.ticket_type,
                                    'imgPath', tkt.ticket_type_img_path,
                                    'description', tkt.ticket_type_description
                            ) as corresponding_ticket_type,
                            get_event_left_ticket_of_given_ticket_type_at_a_given_date(tp.id_event, tkt.ticket_type, null, (?::date)) as left_tickets
                        FROM ticket_price tp
                        LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type
                        WHERE tp.id = ?;
                """;

        Connection con = dataSource.getConnection(TicketPriceInfoDao.class.getName());

        try (PreparedStatement findStmt = con.prepareStatement(sql)) {
            findStmt.setDate(1, Date.valueOf(at));
            findStmt.setString(2, ticketId);

            ResultSet rs = findStmt.executeQuery();

            if (rs.next())
                return ticketPriceInfoMapper.mapFrom(rs);

            throw new RuntimeException(
                    "TicketPriceInfoDao.findAllByEventIdAtAGivenDate :: error while extracting ticket price info"
            );
        } catch (SQLException e) {
            throw new RuntimeException("TicketPriceInfoDao.findAllByEventIdAtAGivenDate :: " +e);
        }

    }
}
