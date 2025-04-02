package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventStatus;
import dev.razafindratelo.tapakilaBackend.entity.enums.TimeZone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EventMapper implements Mapper<Event> {
	private ObjectMapper objectMapper;
    private final UserMapper userMapper;

    @Override
    public Event mapFrom(ResultSet rs) throws SQLException {
        String eventTypesJson = rs.getString("event_types");
        String leftTicketJson = rs.getString("event_left_tickets");
        Set<EventTypeDetail> eventTypes = new HashSet<>();
        List<TicketPriceInfo> leftTickets = new ArrayList<>();

        try {
            if (eventTypesJson != null && !eventTypesJson.isEmpty()) {
                eventTypes = objectMapper.readValue(
                        eventTypesJson,
                        objectMapper.getTypeFactory().constructCollectionType(Set.class, EventTypeDetail.class)
                );
            }
            if (leftTicketJson != null && !leftTicketJson.isEmpty()) {
                leftTickets = objectMapper.readValue(
                        leftTicketJson,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, TicketPriceInfo.class)
                );
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return  Event.builder()
                    .id(rs.getString("event_id"))
                    .organizer(rs.getString("event_organizer"))
                    .title(rs.getString("event_title"))
                    .description(rs.getString("event_description"))
                    .dateTime((rs.getTimestamp("event_date_time").toLocalDateTime()))
                    .timeZone(TimeZone.valueOf(rs.getString("event_time_zone")))
                    .location(rs.getString("event_location"))
                    .locationUrl(rs.getString("event_location_url"))
                    .category(new EventsCategoryMapper().mapFrom(rs))
                    .eventTypeDetail(eventTypes)
                    .imagePath(rs.getString("event_image_path"))
                    .status(EventStatus.valueOf(rs.getString("event_status")))
                    .numberOfTickets(rs.getLong("event_number_of_ticket"))
                    .leftTickets(leftTickets)
                    .maxTicketPerUser(rs.getInt("event_max_ticket_per_user"))
                    .createdAt(rs.getTimestamp("event_created_at").toLocalDateTime())
                    .updatedAt(rs.getTimestamp("event_updated_at").toLocalDateTime())
                    .createdBy(userMapper.mapFrom(rs))
                .build();
    }
}
