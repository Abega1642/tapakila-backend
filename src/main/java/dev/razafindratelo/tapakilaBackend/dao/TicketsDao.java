package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.mapper.TicketsMapper;
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
public class TicketsDao {
    private final DataSource dataSource;
    private final TicketsMapper ticketsMapper;

    
    public long findLastTicketNumber(Connection connection, String eventId) {
        String sql =
                """
                        SELECT COUNT(*)
                        FROM ticket t
                        LEFT JOIN ticket_price tp
                            ON t.id_ticket_price = tp.id
                        WHERE tp.id_event = ?
                """;

        try (PreparedStatement findStmt = connection.prepareStatement(sql)) {
            findStmt.setString(1, eventId);

            ResultSet rs = findStmt.executeQuery();

            if (rs.next())
                return rs.getLong(1);

            throw new RuntimeException("TicketsDao.findLastTicketNumber :: Error while getting last ticket number");
        } catch (SQLException e) {
            throw new RuntimeException("TicketsDao.findLastTicketNumber :: " + e);
        }
    }
   
    public Tickets save(Tickets ticket) {
        String sql =
                """
                INSERT INTO ticket
                (id, ticket_number, qr_code_path, payement_ref, ticket_owner_name, user_email, id_ticket_price, id_payment_mode)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        Connection connection = dataSource.getConnection(TicketsDao.class.getName());
        
        try (PreparedStatement saveStmt = connection.prepareStatement(sql)) {
            saveStmt.setString(1, ticket.getId());
            saveStmt.setLong(2, findLastTicketNumber(connection, ticket.getAssociatedEvent()) + 1);
            saveStmt.setString(3, ticket.getQrCodePath());
            saveStmt.setString(4, ticket.getPaymentRef());
            saveStmt.setString(5, ticket.getTicketOwnerName());
            saveStmt.setString(6,ticket.getPurchasedBy().getEmail());
            saveStmt.setString(7, ticket.getTicketType().getId());
            saveStmt.setString(8, ticket.getPaymentMode().getId());

            int i = saveStmt.executeUpdate();
            if (i > 0)
                return ticket;

            throw new RuntimeException("TicketsDao.save :: error while saving ticket " + ticket);
        } catch (SQLException e) {
            throw new RuntimeException("TicketsDao.save :: " + e);
        }
    }

   
    public List<Tickets> findAllByUserEmail(String userEmail, long page, long size) {
        String sql =
                """
                        WITH TicketInfo AS (
                            SELECT
                                t.id AS ticket_id,
                                t.ticket_number,
                                t.status AS is_enabled,
                                t.purchased_at,
                                t.qr_code_path,
                                t.payement_ref AS payment_ref,
                                t.ticket_owner_name,
                                t.id_ticket_price,
                                t.user_email,
                                t.id_payment_mode,
                                tp.id_event AS associated_event_id
                            FROM ticket t
                                     JOIN ticket_price tp ON t.id_ticket_price = tp.id
                        ),
                        TicketPriceInfo AS (
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
                                tp.id_event AS associated_event_id,
                                JSONB_BUILD_OBJECT(
                                    'id', tkt.ticket_type_id,
                                    'ticketType', tkt.ticket_type,
                                    'imgPath', tkt.ticket_type_img_path,
                                    'description', tkt.ticket_type_description
                                ) AS corresponding_ticket_type,
                                get_event_left_ticket_of_given_ticket_type_at_a_given_date(tp.id_event, tkt.ticket_type, null, (current_date::date)) AS left_tickets
                            FROM ticket_price tp
                            LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type
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
                            ORDER BY percentage DESC
                            LIMIT 5
                        ),
                        PaymentModeInfo AS (
                            SELECT
                                pm.id AS payment_mode_id,
                                pm.description AS payment_mode_description,
                                pm.provider AS payment_mode_provider,
                                pm.type AS payment_mode_type,
                                pm.created_at AS created_at,
                                pm.updated_at AS updated_at,
                                pm.status AS is_active,
                                pm.payment_api_url AS payment_api_url
                            FROM payment_mode pm
                        ),
                        UserInfo AS (
                            SELECT
                                u.email,
                                u.first_name,
                                u.last_name,
                                u.profile_img_path,
                                u.created_at,
                                u.user_role,
                                u.is_active,
                                u.password,
                                COALESCE(
                                    JSON_AGG(
                                        DISTINCT JSONB_BUILD_OBJECT(
                                            'id', t5c.event_category_id,
                                            'event_category', t5c.event_category,
                                            'description', t5c.event_category_description
                                        )
                                    ) FILTER (WHERE t5c.event_category_id IS NOT NULL), '[]'
                                ) AS user_top_5_categories
                            FROM "user" u
                            INNER JOIN Top5Categories AS t5c ON u.email = t5c.user_email
                            GROUP BY u.email
                        )
                        SELECT
                            ti.ticket_id AS id,
                            ti.ticket_number,
                            ti.is_enabled,
                            ti.purchased_at,
                            ti.qr_code_path,
                            ti.payment_ref,
                            ti.ticket_owner_name,
                            JSONB_BUILD_OBJECT(
                                'id', tp.ticket_price_id,
                                'price', tp.ticket_price,
                                'currency', tp.ticket_price_currency,
                                'maxNumber', tp.ticket_price_max_number,
                                'createdAt', tp.ticket_price_created_at,
                                'correspondingTicketType', tp.corresponding_ticket_type,
                                'leftTickets', tp.left_tickets
                            ) AS ticket_info,
                            JSONB_BUILD_OBJECT(
                                'email', ui.email,
                                'firstName', ui.first_name,
                                'lastName', ui.last_name,
                                'profileImgPath', ui.profile_img_path,
                                'userRole', ui.user_role,
                                'userCreatedAt', ui.created_at,
                                'password', ui.password,
                                'userStatus', ui.is_active
                            ) AS purchased_by,
                            ti.associated_event_id AS associated_eventId,
                            JSONB_BUILD_OBJECT(
                                'id', pm.payment_mode_id,
                                'description', pm.payment_mode_description,
                                'paymentType', pm.payment_mode_type,
                                'paymentAPIUrl', pm.payment_api_url,
                                'paymentProvider', pm.payment_mode_provider,
                                'createdAt', pm.created_at,
                                'updatedAt', pm.updated_at,
                                'isActive', pm.is_active
                            ) AS payment_mode
                        FROM TicketInfo ti
                        LEFT JOIN TicketPriceInfo tp ON tp.ticket_price_id = ti.id_ticket_price
                        LEFT JOIN UserInfo ui ON ui.email = ti.user_email
                        LEFT JOIN PaymentModeInfo pm ON pm.payment_mode_id = ti.id_payment_mode
                        WHERE ui.email = ?
                        LIMIT ? OFFSET ?
                """;

        Connection connection = dataSource.getConnection(TicketsDao.class.getName());

        List<Tickets> tickets = new ArrayList<>();

        try (PreparedStatement findAllByUserStmt = connection.prepareStatement(sql)) {
            findAllByUserStmt.setString(1, userEmail.trim());
            findAllByUserStmt.setLong(2, size);
            findAllByUserStmt.setLong(3, size * (page - 1));

            ResultSet rs = findAllByUserStmt.executeQuery();

            while (rs.next()) {
                tickets.add(ticketsMapper.mapFrom(rs));
            }

            return tickets;

        } catch (SQLException e) {
            throw new RuntimeException("TicketsDao.findALlByUserEmail " + e);
        }
    }

}
