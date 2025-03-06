package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class TicketPriceInfo {
    @JsonProperty("id")  private String id;
    @JsonProperty("price") private double price;
    @JsonProperty("currency") private Currency currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("max_number") private long maxNumber;
    @JsonProperty("corresponding_ticket_type") private Ticket ticketType;
    @JsonProperty("left_tickets") private long leftTickets;
    @JsonProperty("associated_event_id") private String associatedEventId;
}
