package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.TicketType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Ticket {
    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("img_path")
    private String imgPath;
    @JsonProperty("ticket_type")
    private TicketType ticketType;

    @Override
    public String toString() {
        return "Ticket {" +
                "\n \t id='" + id + '\'' +
                ",\n \t description='" + description + '\'' +
                ",\n \t imgPath='" + imgPath + '\'' +
                ",\n \t ticketType=" + ticketType +
                "\n \t }";
    }
}
