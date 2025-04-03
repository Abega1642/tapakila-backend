package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketSignature extends TicketPurchase {
    private final String ticketNumber;

    public TicketSignature(String eventId, String userEmail, String owner, String ticketPriceInfoId, String ticketNumber) {
        super(eventId, userEmail, owner, ticketPriceInfoId);
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return "\t Event id = " + getEventId() +
                "\n \t User email = "  + getUserEmail() +
                "\n \t Ticket owner = " + getOwner() +
                "\n \t Ticket price info id = " + getTicketPriceInfoId() +
                "\n \t Ticket number = " + ticketNumber;
    }
}
