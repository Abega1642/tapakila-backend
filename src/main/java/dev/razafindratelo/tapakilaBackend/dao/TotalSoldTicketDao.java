package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.TotalSoldTicket;
import dev.razafindratelo.tapakilaBackend.mapper.TotalSolTicketMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TotalSoldTicketDao {
    private final DataSource dataSource;

    public List<TotalSoldTicket> getTotalSoldTickets(Month month, int year) {
        Connection connection = dataSource.getConnection();
        List<TotalSoldTicket> totalSoldTickets = new ArrayList<>();

        String sqlRequest =
                """
                SELECT
                    tt.ticket_type,
                    COUNT(t.id) AS total_tickets_sold,
                    (
                         SELECT COUNT(*)
                         FROM ticket
                    ) AS total_sold_tickets
                FROM ticket t
                JOIN ticket_price tp ON t.id_ticket_price = tp.id
                JOIN tickets_type tt ON tp.id_ticket_type = tt.id
                WHERE EXTRACT(YEAR FROM t.purchased_at) = ?
                AND EXTRACT(MONTH FROM t.purchased_at) = ?
                GROUP BY tt.ticket_type
                ORDER BY total_tickets_sold DESC;
                """;
        try (PreparedStatement findStmt = connection.prepareStatement(sqlRequest)) {
            findStmt.setInt(1, year);
            findStmt.setInt(2, month.getValue());

            ResultSet rs = findStmt.executeQuery();

            while (rs.next()) {
                TotalSoldTicket stat = new TotalSolTicketMapper().mapFrom(rs);
                stat.setMonth(month);
                stat.setYear(year);

                totalSoldTickets.add(stat);
            }

            return totalSoldTickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
