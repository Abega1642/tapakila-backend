package dev.razafindratelo.tapakilaBackend.entity;

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
    private String id;
    private double price;
    private LocalDateTime createdAt;
    private long maxNumber;
    private Ticket ticketType;
    private Event associatedEvent;
}
