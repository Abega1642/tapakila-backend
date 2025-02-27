package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentType;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymnetProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class PaymentMode {
    private String id;
    private String description;
    private PaymentType paymentType;
    private String paymentAPIUrl;
    private PaymnetProvider paymnetProvider;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
}
