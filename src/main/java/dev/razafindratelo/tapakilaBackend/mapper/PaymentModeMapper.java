package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.PaymentMode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentModeMapper {
    private final ObjectMapper objectMapper;

    public PaymentMode mapFromJson(String json) throws JsonProcessingException {
        PaymentMode paymentMode = null;

        try {
            if (json != null && !json.isEmpty()) {
                paymentMode = objectMapper.readValue(json, PaymentMode.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur de parsing JSON pour correspondingTicketType : " + e.getMessage(), e);
        }

        return paymentMode;
    }
}
