package dev.razafindratelo.tapakilaBackend.service.totalSoldTicketService;

import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYear;
import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYearRange;
import dev.razafindratelo.tapakilaBackend.entity.TotalSoldTicket;
import org.springframework.stereotype.Service;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public interface TotalSoldTicketService {
    List<TotalSoldTicket> getTotalSoldTicketOfCurrentMonth();
    List<SoldTicketStatOverYear> getSoldTicketStatOverYearOfCurrentYear();
    List<TotalSoldTicket> getTotalSoldTicketStatOfMonth(Month month, Year year);
    List<SoldTicketStatOverYear> getTotalSoldTicketStatOverYear(Year year);
    List<SoldTicketStatOverYearRange> getTotalSoldTicketStatOverYearRange(Year startYear, Year endYear);
}
