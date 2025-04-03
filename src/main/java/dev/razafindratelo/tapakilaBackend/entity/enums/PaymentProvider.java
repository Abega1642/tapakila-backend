package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentProvider {
    MVOLA_MADAGASCAR,
    ORANGE_MONEY,
    AIRTEL_MONEY,
    PAYPAL,
    STRIPE,
    VISA,
    MASTERCARD,
    MTN_MOBILE_MONEY,
    GOOGLE_PAY,
    APPLE_PAY,
    CASH,
    BITCOIN;

    @JsonCreator
    public static PaymentProvider fromString(String value) {
        for (PaymentProvider paymentProvider : PaymentProvider.values()) {
            if (paymentProvider.name().equalsIgnoreCase(value)) {
                return paymentProvider;
            }
        }
        return null;
    }
}
