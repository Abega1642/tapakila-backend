package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.razafindratelo.tapakilaBackend.config.LocalDateTimeConfig;
import dev.razafindratelo.tapakilaBackend.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class TicketPriceInfo {
    @JsonProperty("id")
    private String id;

    @JsonProperty("price")
    private double price;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("createdAt")
    @JsonDeserialize(using = LocalDateTimeConfig.class)
    private LocalDateTime createdAt;

    @JsonProperty("maxNumber")
    private long maxNumber;

    @JsonProperty("correspondingTicketType")
    private Ticket ticketType;

    @JsonProperty("leftTickets")
    private long leftTickets;

    @JsonProperty("associatedEventId")
    private String associatedEventId;


    @Override
    public String toString() {
        return "TicketPriceInfo {" +
                "\n \t id='" + id + '\'' +
                ",\n \t price=" + price +
                ",\n \t currency=" + currency +
                ",\n \t createdAt=" + createdAt +
                ",\n \t maxNumber=" + maxNumber +
                ",\n \t ticketType=" + ticketType +
                ",\n \t leftTickets=" + leftTickets +
                ",\n \t associatedEventId='" + associatedEventId + '\'' +
                "\n \t }";
    }
}
