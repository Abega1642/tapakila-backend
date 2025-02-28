package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum TableName {
    EVENT("\"event\" e"),
    USER("\"user\" u"),
    TICKET_PRICE("ticket_price tp"),
    TICKET_TYPE("ticket_type tk"),
    PAYMENT_MODE("payment_mode p"),
    CREATES("creates c"),
    TICKET("ticket t");

    private final String value;
    TableName(String value) {
        this.value = value;
    }
}
