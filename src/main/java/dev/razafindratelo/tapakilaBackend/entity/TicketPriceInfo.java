package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("created_at") private LocalDateTime createdAt;
    @JsonProperty("max_number") private long maxNumber;
    @JsonProperty("type") private Ticket ticketType;
    @JsonProperty("associated_event_id") private String associatedEventId;
}
