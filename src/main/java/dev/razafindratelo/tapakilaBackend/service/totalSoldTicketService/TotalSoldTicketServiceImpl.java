package dev.razafindratelo.tapakilaBackend.service.totalSoldTicketService;

import dev.razafindratelo.tapakilaBackend.dao.TotalSoldTicketDao;
import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYear;
import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYearRange;
import dev.razafindratelo.tapakilaBackend.entity.TotalSoldTicket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TotalSoldTicketServiceImpl implements TotalSoldTicketService {
    private final TotalSoldTicketDao totalSoldTicketDao;

    @Override
    public List<TotalSoldTicket> getTotalSoldTicketOfCurrentMonth() {
        final LocalDate DEFAULT_DATE = LocalDate.now();
        final Year DEFAULT_YEAR = Year.of(DEFAULT_DATE.getYear());
        final Month DEFAULT_MONTH = DEFAULT_DATE.getMonth();

        return totalSoldTicketDao.getTotalSoldTickets(DEFAULT_MONTH, DEFAULT_YEAR.getValue());
    }

    @Override
    public List<SoldTicketStatOverYear> getSoldTicketStatOverYearOfCurrentYear() {
        final LocalDate DEFAULT_DATE = LocalDate.now();
        final Year DEFAULT_YEAR = Year.of(DEFAULT_DATE.getYear());

        return getTotalSoldTicketStatOverYear(DEFAULT_YEAR);
    }

    @Override
    public List<TotalSoldTicket> getTotalSoldTicketStatOfMonth(Month month, Year year) {

        if (month == null) {
            throw new IllegalArgumentException("Month cannot be null");
        }
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        }

        return totalSoldTicketDao.getTotalSoldTickets(month, year.getValue());
    }

    @Override
    public List<SoldTicketStatOverYear> getTotalSoldTicketStatOverYear(Year year) {
        List<SoldTicketStatOverYear> soldTicketStatOverYears = new ArrayList<>();

        for (Month month : Month.values()) {
            List<TotalSoldTicket> totalSoldTickets = totalSoldTicketDao.getTotalSoldTickets(month, year.getValue());

            if (!totalSoldTickets.isEmpty())
                soldTicketStatOverYears.add(
                        new SoldTicketStatOverYear(month, year, totalSoldTickets)
                );
        }

        return soldTicketStatOverYears;
    }

    @Override
    public List<SoldTicketStatOverYearRange> getTotalSoldTicketStatOverYearRange(Year startYear, Year endYear) {
        final LocalDate DEFAULT_DATE = LocalDate.now();
        final Year DEFAULT_YEAR = Year.of(DEFAULT_DATE.getYear());

        if (endYear == null) {
            endYear = DEFAULT_YEAR;
        }

        if (startYear == null) {
            throw new IllegalArgumentException("StartYear cannot be null");
        }

        if (endYear.getValue() < startYear.getValue()) {
            throw new IllegalArgumentException("EndYear must be greater than startYear");
        }

        List<SoldTicketStatOverYearRange> soldTicketStatOverYearRanges = new ArrayList<>();

        for (int year = startYear.getValue(); year <= endYear.getValue(); year++) {

            List<SoldTicketStatOverYear> soldTicketStatOverYear = getTotalSoldTicketStatOverYear(Year.of(year));

            if (!soldTicketStatOverYear.isEmpty())
                soldTicketStatOverYearRanges.add(
                        new SoldTicketStatOverYearRange(
                                 Year.of(year),
                                soldTicketStatOverYear
                        )
                );

        }
        return soldTicketStatOverYearRanges;
    }


}
