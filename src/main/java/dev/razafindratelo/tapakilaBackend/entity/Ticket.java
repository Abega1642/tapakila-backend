package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Ticket {
    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("img_path")
    private String imgPath;
    @JsonProperty("ticket_type")
    private TicketType ticketType;
}
