package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum AvailableColumn {
    // Event columns:
    EVENT_TITLE("e.title"),
    EVENT_DESCRIPTION("e.description"),
    EVENT_DATE_TIME("e.date_time"),
    EVENT_TIME_ZONE("e.time_zone"),
    EVENT_LOCATION("e.location"),
    EVENT_CATEGORY("e.category"),
    EVENT_STATUS("e.status"),
    EVENT_ORGANIZER("(SELECT u.last_name, u.first_name AS organizer)"),
    // User columns:
    USER_EMAIL("u.email"),
    USER_LAST_NAME("u.last_name"),
    USER_FIRST_NAME("u.first_name"),
    USER_ROLE("u.role"),
    USER_EVENTS("u.events"),

    // Ticket :
    TICKET_PRICE("tp.price"),
    TICKET_TYPE("t.type"),
    TICKET_NUMBER("t.number"),
    TICKET_STATUS("t.status"),
    TICKET_PAYMENT_REFERENCE("t.payment_ref"),
    TICKET_OWNER_NAME("t.ticket_owner_name"),
    TICKET_EVENT("t.event"),
    TICKET_DESCRIPTION("tk.description"),

    // Payement_mode :
    PAYMENT_DESCRIPTION("p.payment_description"),
    PAYEMENT_PROVIDER("p.payment_provider"),
    PAYMENT_TYPE("p.payment_type"),
    PAYMENT_API("p.payment_api_url"),
    PAYMENT_STATUS("p.payment_status");

    private final String value;
    AvailableColumn(String value) {
        this.value = value;
    }
}
