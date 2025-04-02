package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {
    private String title;
    private String organizer;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private TimeZone timeZone;
    private String locationUrl;
    private String createdBy;
    private long numberOfTickets;
    private int maxTicketPerUser;
    private EventStatus status;
    private EventCategoryDetail category;
    private Set<EventTypeDetail> eventTypes;
    private LocalDateTime createdAt;
    private List<TicketPriceInfo> ticketsInfo;
}
