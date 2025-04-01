package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.*;
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
        String correspondingTicketType = rs.getString("ticket_info");
        String userJson = rs.getString("purchased_by");
        String paymentTypeJson = rs.getString("payment_mode");

        TicketPriceInfo ticket = null;
        User user = new User();
        PaymentMode pm = null;

        try {
            if (correspondingTicketType != null && !correspondingTicketType.isEmpty()) {
                ticket = objectMapper.readValue(correspondingTicketType, TicketPriceInfo.class);
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
                ticket,
                user,
                rs.getString("associated_eventId"),
                pm
        );
    }
}
