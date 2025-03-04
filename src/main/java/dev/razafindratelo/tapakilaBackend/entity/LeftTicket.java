package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class LeftTicket {
    @JsonProperty("type") private TicketPriceInfo ticketType;
    @JsonProperty("left_number") private long leftTicket;
}
