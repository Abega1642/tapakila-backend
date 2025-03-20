package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@Builder
public class Event {
    private String id;
    private String organizer;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private TimeZone timeZone;
    private String location;
    private String locationUrl;
    private String imagePath;

    @JsonProperty("eventTypes")
    private Set<EventTypeDetail> eventTypeDetail;
    private EventCategoryDetail category;
    private EventStatus status;
    private long numberOfTickets;

    @JsonProperty("eventLeftTickets")
    private List<TicketPriceInfo> leftTickets;
    private int maxTicketPerUser;
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Event { \n" +
                "\n \t  id='" + id + '\'' +
                ",\n \t  organizer='" + organizer + '\'' +
                ",\n \t  title='" + title + '\'' +
                ",\n \t  description='" + description + '\'' +
                ",\n \t  dateTime=" + dateTime +
                ",\n \t  timeZone=" + timeZone +
                ",\n \t  location='" + location + '\'' +
                ",\n \t  locationUrl='" + locationUrl + '\'' +
                ",\n \t  imagePath='" + imagePath + '\'' +
                ",\n \t  eventsType= \n \t" + eventTypeDetail +
                ",\n \t  category=" + category +
                ",\n \t  status=" + status +
                ",\n \t  numberOfTickets=" + numberOfTickets +
                ",\n \t  leftTickets=" + leftTickets +
                ",\n \t  maxTicketPerUser=" + maxTicketPerUser +
                ",\n \t  createdBy=\n \t" + createdBy +
                ",\n \t  createdAt=" + createdAt +
                ",\n \t  updatedAt=" + updatedAt +
                "\n }";
    }
}
