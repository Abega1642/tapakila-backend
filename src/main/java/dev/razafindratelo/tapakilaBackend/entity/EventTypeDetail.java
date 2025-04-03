package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class EventTypeDetail {
    @JsonProperty("id")
    private String id;
    @JsonProperty("eventType")
    private EventType eventType;
    @JsonProperty("description")
    private String description;
    @JsonProperty("correspondingCategories")
    private List<EventCategoryDetail> correspondingCategories;

    @Override
    public String toString() {
        return "EventsType {" +
                "\n \t   id='" + id + '\'' +
                ",\n \t  eventType=" + eventType +
                ",\n \t  description='" + description + '\'' +
                ",\n \t  correspondingCategories= \n \t " + correspondingCategories +
                "\n }";
    }
}
