package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
public class TicketsDao {
    private final DataSource dataSource;

    
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

   
    public Optional<Tickets> findAllByUserEmail(String userEmail) {
        throw new NotImplementedException("Finding ticket by id not implemented yet");
    }

   
    public List<Tickets> findAll(long page, long size) {
        throw new NotImplementedException("Finding all tickets not implemented yet");
    }

   
    public List<Tickets> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding all tickets with criteria not implemented yet");
    }

   
    public List<Tickets> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("Updating ticket not implemented yet");
    }

   
    public Optional<Tickets> delete(String id) {
        throw new NotImplementedException("Deleting ticket not implemented yet");
    }
}
