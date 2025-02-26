package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class TicketPriceInfo {
    private String id;
    private double price;
    private LocalDateTime createdAt;
    private long maxNumber;
    private TicketType ticketType;
    private Event associatedEvent;
}
