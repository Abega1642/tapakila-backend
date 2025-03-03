package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum AvailableColumn {
    //  REQUEST AS COLUMN
    REQUEST_AS_COLUMN("REQUEST", ValueType.REQUEST),
    // Event columns:
    EVENT_ID("e.id", ValueType.STRING),
    EVENT_ID_REQ("e.id", ValueType.REQUEST),

    EVENT_TITLE("e.title", ValueType.STRING),
    EVENT_TITLE_REQ("e.title", ValueType.STRING),
    EVENT_DESCRIPTION("e.description", ValueType.TEXT),
    EVENT_DATE_TIME("e.date_time", ValueType.TIMESTAMP),
    EVENT_DATE_TIME_REQ("e.date_time", ValueType.REQUEST),
    EVENT_TIME_ZONE("e.time_zone", ValueType.TIME_ZONE),
    EVENT_LOCATION("e.location", ValueType.TEXT),
    EVENT_IMAGE_PATH("e.image_path", ValueType.TEXT),
    EVENT_LOCATION_URL("e.location_url", ValueType.TEXT),
    EVENT_NUMBER_OF_TICKET("e.number_of_ticket", ValueType.LONG),
    EVENT_MAX_TICKET_PER_USER("e.max_ticket_per_user", ValueType.INTEGER),
    EVENT_CATEGORY("e.category", ValueType.EVENT_CATEGORY),
    EVENT_STATUS("e.status", ValueType.EVENT_STATUS),
    EVENT_ORGANIZER("e.organizer", ValueType.STRING),

    // User columns:
    USER_EMAIL("u.email", ValueType.STRING),
    USER_LAST_NAME("u.last_name", ValueType.STRING),
    USER_FIRST_NAME("u.first_name", ValueType.STRING),
    USER_PROFILE_IMAGE_PATH("u.profile_img_path", ValueType.TEXT),
    USER_ROLE("u.user_role", ValueType.USER_ROLE),
    USER_STATUS("u.status", ValueType.BOOLEAN),

    //  Event type columns :
    EVENT_TYPE_ID("ety.id", ValueType.STRING),
    EVENT_TYPE__("ety.event_type", ValueType.EVENT_TYPE),
    EVENT_TYPE_DESCRIPTION("ety.description", ValueType.TEXT),

    //  Event category columns :
    EVENT_CATEGORY_ID("ec.id", ValueType.STRING),
    EVENT_CATEGORY_DESCRIPTION("ec.description", ValueType.TEXT),
    EVENT_CATEGORY__("ec.event_category", ValueType.EVENT_CATEGORY),

    //  Creates Columns :
    CREATES_CREATED_AT("c.created_at", ValueType.TIMESTAMP),
    CREATES_CREATED_AT_REQ("c.created_at", ValueType.REQUEST),
    CREATES_UPDATED_AT("c.updated_at", ValueType.TIMESTAMP),
    CREATES_UPDATED_AT_REQ("c.updated_at", ValueType.REQUEST),

    // Ticket :
    TICKET_PRICE("tp.price", ValueType.FLOAT),
    TICKET_IMG_PATH("tk.img_path", ValueType.TEXT),
    TICKET_TYPE("t.type", ValueType.TICKET_TYPE),
    TICKET_NUMBER("t.number", ValueType.LONG),
    TICKET_STATUS("t.status", ValueType.BOOLEAN),
    TICKET_PAYMENT_REFERENCE("t.payment_ref", ValueType.TEXT),
    TICKET_OWNER_NAME("t.ticket_owner_name", ValueType.TEXT),
    TICKET_EVENT("t.id_event", ValueType.STRING),
    TICKET_DESCRIPTION("tk.description", ValueType.TEXT),

    // Payement_mode :
    PAYMENT_DESCRIPTION("p.payment_description", ValueType.TEXT),
    PAYEMENT_PROVIDER("p.payment_provider", ValueType.PAYMENT_MODE_PROVIDER),
    PAYMENT_TYPE("p.payment_type", ValueType.PAYMENT_MODE_TYPE),
    PAYMENT_API("p.payment_api_url", ValueType.TEXT),
    PAYMENT_LOGO_IMG_PATH("p.payment_logo_img_path", ValueType.TEXT),
    PAYMENT_STATUS("p.payment_status", ValueType.BOOLEAN),;

    private final String value;
    private final ValueType valueType;
    AvailableColumn(String value, ValueType valueType) {
        this.value = value;
        this.valueType = valueType;
    }
}
