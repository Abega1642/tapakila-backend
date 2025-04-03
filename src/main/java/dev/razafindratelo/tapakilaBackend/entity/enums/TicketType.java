package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TicketType {
    VIP,
    STANDARD,
    EARLY_BIRD;

    @JsonCreator
    public static TicketType fromString(String value) {
        for (TicketType ticketType : TicketType.values()) {
            if (ticketType.name().equalsIgnoreCase(value)) {
                return ticketType;
            }
        }
        return null;
    }
}
