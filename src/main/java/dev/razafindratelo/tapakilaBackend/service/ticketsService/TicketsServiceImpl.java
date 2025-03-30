package dev.razafindratelo.tapakilaBackend.service.ticketsService;

import dev.razafindratelo.tapakilaBackend.dao.TicketsDao;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketsServiceImpl implements TicketsService {
    private final TicketsDao ticketsDao;

    @Override
    public Tickets save(Tickets ticket) {
        throw new NotImplementedException("Not implemented yet");
    }
}
