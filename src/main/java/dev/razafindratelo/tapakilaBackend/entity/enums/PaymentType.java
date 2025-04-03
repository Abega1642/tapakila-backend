package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentType {
    MOBILE_MONEY,
    VISA,
    MASTERCARD,
    PAYPAL,
    CRYPTOCURRENCY,
    BANK_TRANSFER,
    CASH;

    @JsonCreator
    public static PaymentType fromString(String value) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.name().equalsIgnoreCase(value)) {
                return paymentType;
            }
        }
        return null;
    }
}
