package dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService;

import dev.razafindratelo.tapakilaBackend.dao.TicketPriceInfoDao;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class TicketPriceInfoServiceImpl implements TicketPriceInfoService {
    private final TicketPriceInfoDao ticketPriceInfoDao;

    @Override
    public List<TicketPriceInfo> saveTicketPriceInfos(List<TicketPriceInfo> ticketPriceInfos, String eventId) {
        throw new NotImplementedException("SaveTicketPriceInfos not implemented");
    }

    @Override
    public List<TicketPriceInfo> getAllTicketPriceInfosByEventId(String eventId) {
        if (eventId == null || eventId.isEmpty())
            throw new IllegalArgumentException("Event id cannot be null or empty");

        return ticketPriceInfoDao.findAllByEventIdAtAGivenDate(eventId, LocalDate.now());
    }

    @Override
    public TicketPriceInfo getTicketPriceInfoById(String ticketId) {
        if (ticketId.trim().isEmpty())
            throw new IllegalArgumentException("Ticket id cannot be null or empty");

        return ticketPriceInfoDao.findTicketPriceInfoById(ticketId, LocalDate.now());
    }

    @Override
    public List<TicketPriceInfo> getAllTicketPriceInfosByEventIdAtAGivenDate(String eventId, LocalDate at) {
        if (eventId == null || eventId.isEmpty())
            throw new IllegalArgumentException("Event id cannot be null or empty");

        if (at == null)
            return getAllTicketPriceInfosByEventId(eventId);

        return ticketPriceInfoDao.findAllByEventIdAtAGivenDate(eventId, at);
    }




}
