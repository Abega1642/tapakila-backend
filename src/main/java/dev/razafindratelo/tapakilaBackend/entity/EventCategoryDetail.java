package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class EventCategoryDetail {
    @JsonProperty("id")
    private String id;
    @JsonProperty("eventCategory")
    private EventCategory eventCategory;
    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return "EventsCategory {" +
                "\n \t  id='" + id + '\'' +
                ",\n \t  eventCategory=" + eventCategory +
                ",\n \t  description='" + description + '\'' +
                "\n }";
    }
}
