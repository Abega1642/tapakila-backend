package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum AvailableColumn {
    //  REQUEST AS COLUMN
    REQUEST_AS_COLUMN("REQUEST"),
    // Event columns:
    EVENT_ID("e.id"),
    EVENT_TITLE("e.title"),
    EVENT_DESCRIPTION("e.description"),
    EVENT_DATE_TIME("e.date_time"),
    EVENT_TIME_ZONE("e.time_zone"),
    EVENT_LOCATION("e.location"),
    EVENT_IMAGE_PATH("e.image_path"),
    EVENT_LOCATION_URL("e.location_url"),
    EVENT_NUMBER_OF_TICKET("e.number_of_ticket"),
    EVENT_MAX_TICKET_PER_USER("e.max_ticket_per_user"),
    EVENT_CATEGORY("e.category"),
    EVENT_STATUS("e.status"),
    EVENT_ORGANIZER("e.organizer"),

    // User columns:
    USER_EMAIL("u.email"),
    USER_LAST_NAME("u.last_name"),
    USER_FIRST_NAME("u.first_name"),
    USER_PROFILE_IMAGE_PATH("u.profile_img_path"),
    USER_ROLE("u.user_role"),
    USER_STATUS("u.status"),

    //  Event type columns :
    EVENT_TYPE_ID("ety.id"),
    EVENT_TYPE__("ety.event_type"),
    EVENT_TYPE_DESCRIPTION("ety.description"),

    //  Event category columns :
    EVENT_CATEGORY_ID("ec.id"),
    EVENT_CATEGORY_DESCRIPTION("ec.description"),
    EVENT_CATEGORY__("ec.event_category"),

    //  Creates Columns :
    CREATES_CREATED_AT("c.created_at"),
    CREATES_UPDATED_AT("c.updated_at"),

    // Ticket :
    TICKET_PRICE("tp.price"),
    TICKET_IMG_PATH("tk.img_path"),
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
    PAYMENT_LOGO_IMG_PATH("p.payment_logo_img_path"),
    PAYMENT_STATUS("p.payment_status");

    private final String value;
    AvailableColumn(String value) {
        this.value = value;
    }
}
