package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Tickets {
    private String id;
    private long ticketNumber;
    private boolean isEnabled;
    private LocalDateTime purchasedAt;
    private String qrCodePath;
    private String paymentRef;
    private String ticketOwnerName;

    @JsonProperty("ticketType")
    private TicketPriceInfo ticketType;

    @JsonProperty("purchasedBy")
    private User purchasedBy;

	@JsonProperty("associatedEventId")
    private String associatedEvent;

    @JsonProperty("paymentMode")
    private PaymentMode paymentMode;


    public Tickets(
            long ticketNumber,
            String qrCodePath,
            String ticketOwnerName,
            TicketPriceInfo ticketType,
            User purchasedBy,
            String associatedEvent,
            PaymentMode paymentMode
    ) {
        this.id = generateId();
        this.isEnabled = true;
        this.ticketNumber = ticketNumber;
        this.qrCodePath = qrCodePath;
        this.paymentRef = UUID.randomUUID().toString();
        this.ticketOwnerName = ticketOwnerName;
        this.ticketType = ticketType;
        this.purchasedBy = purchasedBy;
        this.associatedEvent = associatedEvent;
        this.paymentMode = paymentMode;
    }

    private static String generateId() {
        return "$Tkt-" + UUID.randomUUID().toString();
    }


    @Override
    public String toString() {
        return "Tickets {" +
                "\n \t id='" + id + '\'' +
                ",\n \t ticketNumber=" + ticketNumber +
                ",\n \t isEnabled=" + isEnabled +
                ",\n \t purchasedAt=" + purchasedAt +
                ",\n \t qrCodePath='" + qrCodePath + '\'' +
                ",\n \t paymentRef='" + paymentRef + '\'' +
                ",\n \t ticketOwnerName='" + ticketOwnerName + '\'' +
                ",\n \t ticketType=" + ticketType +
                ",\n \t purchasedBy=" + purchasedBy +
                ",\n \t associatedEvent='" + associatedEvent + '\'' +
                ",\n \t paymentMode=" + paymentMode +
                "\n \t }";
    }
}
