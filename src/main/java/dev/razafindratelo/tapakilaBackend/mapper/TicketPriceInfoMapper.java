package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.dto.TicketPriceInfoDto;
import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.enums.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class TicketPriceInfoMapper implements Mapper<TicketPriceInfo> {
    private final ObjectMapper objectMapper;

    @Override
    public TicketPriceInfo mapFrom(ResultSet rs) throws SQLException {
        String correspondingTicketType = rs.getString("corresponding_ticket_type");

        Ticket correspondingTicket = null;

        try {
            if (correspondingTicketType != null && !correspondingTicketType.isEmpty()) {
                correspondingTicket = objectMapper.readValue(correspondingTicketType, Ticket.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur de parsing JSON pour correspondingTicketType : " + e.getMessage(), e);
        }

        return new TicketPriceInfo(
                rs.getString("ticket_price_id"),
                rs.getDouble("ticket_price"),
                Currency.valueOf(rs.getString("ticket_price_currency")),
                rs.getTimestamp("ticket_price_created_at").toLocalDateTime(),
                rs.getLong("ticket_price_max_number"),
                correspondingTicket,
                rs.getLong("left_tickets"),
                rs.getString("associated_event_id")
        );
    }

    public static TicketPriceInfo mapFrom(TicketPriceInfoDto ticketPriceInfoDto, String eventId) {

        return new TicketPriceInfo(
                ticketPriceInfoDto.getId(),
                ticketPriceInfoDto.getPrice(),
                ticketPriceInfoDto.getCurrency(),
                ticketPriceInfoDto.getCreatedAt(),
                ticketPriceInfoDto.getMaxNumber(),
                ticketPriceInfoDto.getTicketType(),
                ticketPriceInfoDto.getLeftTickets(),
                eventId
        );
    }
}

