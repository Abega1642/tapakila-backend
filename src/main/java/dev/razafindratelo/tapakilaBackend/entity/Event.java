package dev.razafindratelo.tapakilaBackend.entity;

import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import lombok.*;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode
@ToString
@Builder
public class Event {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private TimeZone timeZone;
    private String location;
    private String imagePath;
    private EventCategory category;
    private EventStatus status;
    private long numberOfTickets;
    private int maxTicketPerUser = 5;
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
