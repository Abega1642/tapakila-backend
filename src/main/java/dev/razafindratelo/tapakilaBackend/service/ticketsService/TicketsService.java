package dev.razafindratelo.tapakilaBackend.service.ticketsService;

import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import org.springframework.stereotype.Service;

@Service
public interface TicketsService {
    Tickets save(Tickets ticket);
}
