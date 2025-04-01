package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonProperty("id")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("paymentType")
    private PaymentType paymentType;

    @JsonProperty("paymentAPIUrl")
    private String paymentAPIUrl;

    @JsonProperty("paymentProvider")
    private PaymentProvider paymentProvider;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("isActive")
    private boolean status;
}
