package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Turnover {
    @JsonProperty("turnover")
    private double turnover;

    @JsonProperty("ticketInfo")
    private TicketPriceInfo ticketInfo;

    @Override
    public String toString() {
        return "Turnover {"+
                  "\n \t turnover=" + turnover +
                   "\n \t ticketInfo=" + ticketInfo +
                "\n}";
    }
}
