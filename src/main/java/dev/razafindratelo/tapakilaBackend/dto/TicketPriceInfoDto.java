package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketPriceInfoDto {
    private String id;
    private double price;
    private Currency currency;
    private LocalDateTime createdAt;
    private long maxNumber;
    private Ticket ticketType;
    private long leftTickets;

}
