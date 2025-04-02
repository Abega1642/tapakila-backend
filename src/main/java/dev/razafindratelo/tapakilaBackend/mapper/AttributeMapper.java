package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;

public class AttributeMapper {

    public static AvailableColumn getTableNameFromTableName(String attribute) {
        return switch (attribute) {
            //  EVENT
            case "dateTime"         -> AvailableColumn.EVENT_DATE_TIME;
            case "createdAt"        -> AvailableColumn.CREATES_CREATED_AT;
            case "updatedAt"        -> AvailableColumn.CREATES_UPDATED_AT;
            case "eventId"          -> AvailableColumn.EVENT_ID;
            case "eCategory"        -> AvailableColumn.EVENT_CATEGORY;
			case "eStatus"        	-> AvailableColumn.EVENT_STATUS;
            case "type"             -> AvailableColumn.EVENT_TYPE__;
            case "eDescription"     -> AvailableColumn.EVENT_DESCRIPTION;
            case "eLocation"        -> AvailableColumn.EVENT_LOCATION;
            case "organizer"        -> AvailableColumn.EVENT_ORGANIZER;
            case "title"            -> AvailableColumn.EVENT_TITLE;
            case "maxTicketPerUser" -> AvailableColumn.EVENT_MAX_TICKET_PER_USER;

            default -> throw new IllegalStateException("Unexpected attribute value: " + attribute);
        };
    }
}
