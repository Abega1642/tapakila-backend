package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrimitiveEventTicketDto {
    private String eventId;
    private List<Tickets> associatedTickets;
}
