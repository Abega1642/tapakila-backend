package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.EventTurnover;
import dev.razafindratelo.tapakilaBackend.entity.Turnover;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class EventTurnoverMapper implements Mapper<EventTurnover> {
    private final ObjectMapper objectMapper;

    @Override
    public EventTurnover mapFrom(ResultSet rs) throws SQLException {
        String eventTurnoverJSON = rs.getString("tickets");
        List<Turnover> turnovers = new ArrayList<>();

        try {
            if (eventTurnoverJSON != null && !eventTurnoverJSON.isEmpty()) {
                turnovers = objectMapper.readValue(
                        eventTurnoverJSON,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Turnover.class)
                );
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new EventTurnover(
                rs.getString("event_id"),
                turnovers
        );
    }
}
