package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventsCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements Mapper<Event> {

    @Override
    public Event mapFrom(ResultSet rs) throws SQLException {
        return new Event.Builder()
                .id(rs.getString("event_id"))
                .organizer(rs.getString("event_organizer"))
                .title(rs.getString("event_title"))
                .description(rs.getString("event_description"))
                .dateTime((rs.getTimestamp("event_date_time").toLocalDateTime()))
                .timeZone(TimeZone.valueOf(rs.getString("event_time_zone")))
                .location(rs.getString("event_location"))
                .locationUrl(rs.getString("event_location_url"))
                .category(new EventsCategoryMapper().mapFrom(rs))
                .eventsType(null)
                .imagePath(rs.getString("event_image_path"))
                .status(EventStatus.valueOf(rs.getString("event_status")))
                .numberOfTickets(rs.getLong("event_number_of_ticket"))
                .maxTicketPerUser(rs.getInt("event_max_ticket_per_user"))
                .createdAt(rs.getTimestamp("event_created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("event_updated_at").toLocalDateTime())
                .createdBy(new UserMapper().mapFrom(rs))
                .build();
    }
}
