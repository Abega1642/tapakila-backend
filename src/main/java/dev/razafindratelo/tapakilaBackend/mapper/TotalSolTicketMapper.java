package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.TotalSoldTicket;
import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TotalSolTicketMapper implements Mapper<TotalSoldTicket>{
    @Override
    public TotalSoldTicket mapFrom(ResultSet rs) throws SQLException {
        long sold = rs.getLong("total_tickets_sold");
        long totalTickets = rs.getLong("total_sold_tickets");
        double percentage = (double) sold / totalTickets;

        return new TotalSoldTicket(
                TicketType.valueOf(rs.getString("ticket_type")),
                null,
                0,
                sold,
                percentage
        );
    }
}
