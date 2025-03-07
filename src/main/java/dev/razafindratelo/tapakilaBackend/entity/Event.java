package dev.razafindratelo.tapakilaBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
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

    @JsonProperty("event_types")
    private Set<EventTypeDetail> eventTypeDetail;
    private EventCategoryDetail category;
    private EventStatus status;
    private long numberOfTickets;

    @JsonProperty("event_left_tickets")
    private List<TicketPriceInfo> leftTickets;
    private int maxTicketPerUser;
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Event(
            String id,
            String organizer,
            String title,
            String description,
            LocalDateTime dateTime,
            TimeZone timeZone,
            String location,
            String locationUrl,
            String imagePath,
            Set<EventTypeDetail> eventTypeDetail,
            EventCategoryDetail category,
            EventStatus status,
            long numberOfTickets,
            List<TicketPriceInfo> leftTickets,
            int maxTicketPerUser,
            User createdBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.organizer = organizer;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.timeZone = timeZone;
        this.location = location;
        this.locationUrl = locationUrl;
        this.imagePath = imagePath;
        this.eventTypeDetail = eventTypeDetail;
        this.category = category;
        this.status = status;
        this.numberOfTickets = numberOfTickets;
        this.leftTickets = leftTickets;
        this.maxTicketPerUser = maxTicketPerUser;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static class Builder {
        private String id = "No id";
        private String organizer = "No organizer";
        private String title = "No title";
        private String description = "No description to this event";
        private LocalDateTime dateTime = LocalDateTime.now();
        private TimeZone timeZone = TimeZone.UTC_3_EAST_AFRICA_TIME;
        private String location = "No where";
        private String locationUrl = "https://_no_url_found.com";
        private String imagePath = "No image founded";
        private Set<EventTypeDetail> eventTypeDetail = null;
        private EventCategoryDetail category = null;
        private EventStatus status = EventStatus.DRAFT;
        private long numberOfTickets = 0;
        private List<TicketPriceInfo> leftTickets;
        private int maxTicketPerUser = 5;
        private User createdBy = null;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder organizer(String organizer) {
            this.organizer = organizer;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder timeZone(TimeZone timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder locationUrl(String locationUrl) {
            this.locationUrl = locationUrl;
            return this;
        }

        public Builder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder eventsType(Set<EventTypeDetail> eventTypeDetail) {
            this.eventTypeDetail = eventTypeDetail;
            return this;
        }

        public Builder category(EventCategoryDetail category) {
            this.category = category;
            return this;
        }
        public Builder status(EventStatus status) {
            this.status = status;
            return this;
        }

        public Builder numberOfTickets(long numberOfTickets) {
            this.numberOfTickets = numberOfTickets;
            return this;
        }

        public Builder leftTickets(List<TicketPriceInfo> leftTickets) {
            this.leftTickets = leftTickets;
            return this;
        }

        public Builder maxTicketPerUser(int maxTicketPerUser) {
            this.maxTicketPerUser = maxTicketPerUser;
            return this;
        }

        public Builder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Event build() {
            return new Event(
                    this.id,
                    this.organizer,
                    this.title,
                    this.description,
                    this.dateTime,
                    this.timeZone,
                    this.location,
                    this.locationUrl,
                    this.imagePath,
                    this.eventTypeDetail,
                    this.category,
                    this.status,
                    this.numberOfTickets,
                    this.leftTickets,
                    this.maxTicketPerUser,
                    this.createdBy,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }

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
