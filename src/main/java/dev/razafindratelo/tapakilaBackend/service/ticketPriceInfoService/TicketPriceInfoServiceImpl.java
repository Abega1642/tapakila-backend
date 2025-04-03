package dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService;

import dev.razafindratelo.tapakilaBackend.dao.TicketPriceInfoDao;
import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TicketPriceInfoServiceImpl implements TicketPriceInfoService {
    private final TicketPriceInfoDao ticketPriceInfoDao;

    @Override
    public List<TicketPriceInfo> saveTicketPriceInfos(List<TicketPriceInfo> ticketPriceInfos, String eventId) {
        if (ticketPriceInfos.isEmpty())
            return ticketPriceInfos;

        if (eventId.trim().isEmpty())
            throw new IllegalArgumentException("Event id needs to be specified");

        ticketPriceInfos.forEach(tpf -> {
            if (tpf.getId() == null || tpf.getId().isEmpty() || !tpf.getAssociatedEventId().equals(eventId))
                throw new IllegalStateException("TicketPriceInfo saveTicketPriceInfos :: associated event doesn't correspond or id empty or null");

            if (tpf.getTicketType() == null)
                throw new IllegalArgumentException("TicketPriceInfo.saveTicketPriceInfos :: ticketType is null");
        });

        List<TicketPriceInfo> savedTPFs = new ArrayList<>();
        ticketPriceInfos.forEach(tpf -> savedTPFs.add(ticketPriceInfoDao.saveEventTicketPriceInfo(tpf)));

        return savedTPFs;
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
