package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentType;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentProvider;
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
    @JsonProperty("paymentProvider")
    private PaymentProvider paymentProvider;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
}
