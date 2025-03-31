package dev.razafindratelo.tapakilaBackend.service.ticketsService;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface TicketsService {
    Tickets save(TicketPurchase ticket) throws IOException, WriterException;
    List<Tickets> saveAll(List<TicketPurchase> ticketPurchases) throws IOException;
}
