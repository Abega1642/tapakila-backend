package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Month;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalSoldTicket {
    private TicketType ticketType;
    private Month month;
    private int year;
    private long sold;
    private double percentage;
}
