package dev.razafindratelo.tapakilaBackend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventTypeDetailMapper implements Mapper<EventTypeDetail> {
    @Override
    public EventTypeDetail mapFrom(ResultSet rs) throws SQLException {

        ObjectMapper objectMapper = new ObjectMapper();
        String eventCategoriesJSON = rs.getString("corresponding_categories");
        mapEventDetailCategoryJSONB(objectMapper, eventCategoriesJSON);
        List<EventCategoryDetail> eventCategories = mapEventDetailCategoryJSONB(objectMapper, eventCategoriesJSON);


        return new EventTypeDetail(
                rs.getString("event_type_id"),
                EventType.valueOf(rs.getString("event_type")),
                rs.getString("event_type_description"),
                eventCategories
        );
    }

    static List<EventCategoryDetail>  mapEventDetailCategoryJSONB(ObjectMapper objectMapper, String eventCategoriesJSON) {
        List<EventCategoryDetail> eventCategories = new ArrayList<>();

        try {
            if (eventCategoriesJSON != null && !eventCategoriesJSON.isEmpty()) {
                eventCategories = objectMapper.readValue(
                        eventCategoriesJSON,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, EventCategoryDetail.class)
                );
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
         return eventCategories;
    }
}
