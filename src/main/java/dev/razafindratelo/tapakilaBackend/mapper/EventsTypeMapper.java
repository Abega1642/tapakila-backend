package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.EventsType;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventsTypeMapper implements Mapper<EventsType> {
    @Override
    public EventsType mapFrom(ResultSet rs) throws SQLException {
        return new EventsType(
                rs.getString("event_type_id"),
                EventType.valueOf(rs.getString("event_type")),
                rs.getString("event_type_description"),
                List.of()
        );
    }
}
