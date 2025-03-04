package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum ValueType {
    //  ENUMS :
    EVENT_TYPE("event_type"),
    EVENT_CATEGORY("event_category"),
    EVENT_STATUS("event_status"),
    TICKET_TYPE("ticket_type"),
    PAYMENT_MODE_TYPE("payment_mode_type"),
    PAYMENT_MODE_PROVIDER("payment_mode_provider"),
    USER_ROLE("user_role"),
    TIME_ZONE("time_zone"),

    //  SQL types :
    ARRAY("array"),
    STRING("varchar"),
    INTEGER("int"),
    LONG("int8"),
    DATE("date"),
    FLOAT("float"),
    TIMESTAMP("timestamp"),
    BOOLEAN("boolean"),
    TEXT("text"),
    REQUEST("request");

    private final String representation;

    ValueType(String representation) {
        this.representation = representation;
    }
}
