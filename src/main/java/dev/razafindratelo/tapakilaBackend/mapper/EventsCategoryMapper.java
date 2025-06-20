package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsCategoryMapper implements Mapper<EventCategoryDetail> {
    @Override
    public EventCategoryDetail mapFrom(ResultSet rs) throws SQLException {
        return new EventCategoryDetail(
                rs.getString("event_category_id"),
                EventCategory.valueOf(rs.getString("event_category")),
                rs.getString("event_category_description")
        );
    }
}
