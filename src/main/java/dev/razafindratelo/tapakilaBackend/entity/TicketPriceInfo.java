package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import dev.razafindratelo.tapakilaBackend.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class TicketPriceInfo {
    @JsonProperty("id")  private String id;
    @JsonProperty("price") private double price;
    @JsonProperty("currency") private Currency currency;

    @JsonProperty("created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
	private LocalDateTime createdAt;
    @JsonProperty("max_number") private long maxNumber;
    @JsonProperty("corresponding_ticket_type") private Ticket ticketType;
    @JsonProperty("left_tickets") private long leftTickets;
    @JsonProperty("associated_event_id") private String associatedEventId;

    @Override
    public String toString() {
        return "TicketPriceInfo {" +
                "\n \t id='" + id + '\'' +
                ",\n \t price=" + price +
                ",\n \t currency=" + currency +
                ",\n \t createdAt=" + createdAt +
                ",\n \t maxNumber=" + maxNumber +
                ",\n \t ticketType=" + ticketType +
                ",\n \t leftTickets=" + leftTickets +
                ",\n \t associatedEventId='" + associatedEventId + '\'' +
                "\n \t }";
    }
}
