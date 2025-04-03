package dev.razafindratelo.tapakilaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SoldTicketStatOverYearRange {
    private Year year;
    private List<SoldTicketStatOverYear> soldTicketStatOverYears;
}
