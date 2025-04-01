package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.PaymentMode;
import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@Component
public class TicketsMapper implements Mapper<Tickets> {
    private final ObjectMapper objectMapper;

    @Override
    public Tickets mapFrom(ResultSet rs) throws SQLException {
        String correspondingTicketType = rs.getString("corresponding_ticket_type");
        String userJson = rs.getString("purchasedBy");
        String paymentTypeJson = rs.getString("payment_mode");

        Ticket ticket = new Ticket();
        User user = new User();
        PaymentMode pm = null;

        try {
            if (correspondingTicketType != null && !correspondingTicketType.isEmpty()) {
                ticket = objectMapper.readValue(correspondingTicketType, Ticket.class);
            }

            if (userJson != null && !userJson.isEmpty()) {
                user = objectMapper.readValue(userJson, User.class);
            }

            if (paymentTypeJson != null && !paymentTypeJson.isEmpty()) {
                pm = objectMapper.readValue(paymentTypeJson, PaymentMode.class);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur de parsing JSON pour correspondingTicketType : " + e.getMessage(), e);
        }

        return new Tickets(
                rs.getString("id"),
                rs.getLong("ticket_number"),
                rs.getBoolean("is_enabled"),
                rs.getTimestamp("purchased_at").toLocalDateTime(),
                rs.getString("qr_code_path"),
                rs.getString("payment_ref"),
                rs.getString("ticket_owner_name"),
                null,
                null,
                null,
                null
        );
    }
}
