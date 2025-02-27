package dev.razafindratelo.tapakilaBackend.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Tickets {
    private String id;
    private long ticketNumber;
    private boolean isEnabled;
    private LocalDateTime purchasedAt;
    private String qrCodePath;
    private String paymentRef;
    private String ticketOwnerName;
    private Ticket ticketType;
    private User purchasedBy;
    private Event associatedEvent;
    private PaymentMode paymentMode;
}
