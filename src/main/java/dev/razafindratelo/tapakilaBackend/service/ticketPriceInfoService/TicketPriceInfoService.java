package dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService;

import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public interface TicketPriceInfoService {
    List<TicketPriceInfo> getAllTicketPriceInfosByEventId(String eventId);
    TicketPriceInfo getTicketPriceInfoById(String ticketId);
    List<TicketPriceInfo> getAllTicketPriceInfosByEventIdAtAGivenDate(String eventId, LocalDate at);
}
