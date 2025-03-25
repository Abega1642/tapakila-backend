package dev.razafindratelo.tapakilaBackend.controller;

import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYear;
import dev.razafindratelo.tapakilaBackend.dto.SoldTicketStatOverYearRange;
import dev.razafindratelo.tapakilaBackend.entity.TotalSoldTicket;
import dev.razafindratelo.tapakilaBackend.service.totalSoldTicketService.TotalSoldTicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.Month;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("tickets-stats")
@AllArgsConstructor
public class TotalSoldTicketController {
    private final TotalSoldTicketService totalSoldTicketService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("current-year")
    public ResponseEntity<List<SoldTicketStatOverYear>> getTicketsStatOfCurrentYear() {
        return ResponseEntity.ok(totalSoldTicketService.getSoldTicketStatOverYearOfCurrentYear());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("current-month")
    public ResponseEntity<List<TotalSoldTicket>> getTotalSoldTicketStatOfCurrentMonth() {
        return ResponseEntity.ok(totalSoldTicketService.getTotalSoldTicketOfCurrentMonth());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("month/{month}/of-year/{year}")
    public ResponseEntity<List<TotalSoldTicket>> getTotalSoldTicketStatOfMonth(
            @PathVariable("year") Year year,
            @PathVariable("month") Month month
    ) {
        return ResponseEntity.ok(totalSoldTicketService.getTotalSoldTicketStatOfMonth(month, year));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("over-year-range")
    public ResponseEntity<List<SoldTicketStatOverYearRange>> getTotalSOldTicketStatOverYearRange(
            @RequestParam("startYear") Year startYear,
            @RequestParam(value = "endYear", required = false) Year endYear
    ) {
        return ResponseEntity.ok(totalSoldTicketService.getTotalSoldTicketStatOverYearRange(startYear, endYear));
    }
}
