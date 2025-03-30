package dev.razafindratelo.tapakilaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketPurchase {
    private String eventId;
    private String userEmail;
    private String owner;
    private String ticketPriceInfoId;
    private int quantity;

    @Override
    public String toString() {
        return "{" +
                "\n \t eventId = " + eventId +
                "\n \t userEmail = "  + userEmail +
                "\n \t owner = " + owner +
                "\n \t ticketPriceInfoId = " + ticketPriceInfoId +
                "\n \t quantity = " + quantity +
                "\n}";
    }
}
