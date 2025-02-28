package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventsType {
    private String id;
    private EventType eventType;
    private String description;
    private List<EventsCategory> correspondingCategories;
}
