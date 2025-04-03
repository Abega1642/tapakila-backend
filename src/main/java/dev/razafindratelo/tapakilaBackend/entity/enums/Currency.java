package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Currency {
    MGA, DOLLAR, EURO;

    @JsonCreator
    public static Currency fromString(String value) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equalsIgnoreCase(value)) {
                return currency;
            }
        }
        return null;
    }
}
