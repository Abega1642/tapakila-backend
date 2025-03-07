package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Builder
public class Tickets {
    private String id;
    private long ticketNumber;
    private boolean isEnabled;
    private LocalDateTime purchasedAt;
    private String qrCodePath;
    private String paymentRef;
    private String ticketOwnerName;
    @JsonProperty("ticket_type")
    private Ticket ticketType;
    @JsonProperty("purchased_by")
    private User purchasedBy;
    private String associatedEvent;
    @JsonProperty("payment_mode")
    private PaymentMode paymentMode;

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
