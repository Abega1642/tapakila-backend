package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ColumnAliasQueryFactoryTest {

    @Test
    void makeSubInsertQuery_with_no_column() {
        String expected = "";
        String actual = ColumnAliasQueryFactory.makeSubInsertQuery(List.of()).toString();

        assertEquals(expected, actual);
    }

    @Test
    void makeSubInsertQuery_for_event_columns() {
        List<Column> columns = List.of(
                Column.from(AvailableColumn.EVENT_ID),
                Column.from(AvailableColumn.EVENT_ORGANIZER),
                Column.from(AvailableColumn.EVENT_TITLE),
                Column.from(AvailableColumn.EVENT_DESCRIPTION),
                Column.from(AvailableColumn.EVENT_DATE_TIME),
                Column.from(AvailableColumn.EVENT_TIME_ZONE),
                Column.from(AvailableColumn.EVENT_LOCATION),
                Column.from(AvailableColumn.EVENT_LOCATION_URL),
                Column.from(AvailableColumn.EVENT_IMAGE_PATH),
                Column.from(AvailableColumn.EVENT_CATEGORY),
                Column.from(AvailableColumn.EVENT_STATUS),
                Column.from(AvailableColumn.EVENT_NUMBER_OF_TICKET),
                Column.from(AvailableColumn.EVENT_MAX_TICKET_PER_USER)
        );

        String expectedColumns = " (id, organizer, title, description, date_time, time_zone, location, "+
                "location_url, image_path, category, status, number_of_ticket, max_ticket_per_user)";
        String expectedParamFields = " ((?::varchar), (?::varchar), (?::varchar), (?::text), (?::timestamp), (?::time_zone), "+
                "(?::text), (?::text), (?::text), (?::event_category), (?::event_status), (?::int8), (?::int))";

        String actualColumns = ColumnAliasQueryFactory.makeSubInsertQuery(columns).toString();
        String actualParamFields = ColumnAliasQueryFactory.makeSubInsertValuesFieldQuery(columns).toString();

        assertEquals(expectedColumns, actualColumns);
        assertEquals(expectedParamFields, actualParamFields);
    }

    @Test
    void makeSubInsertQuery_for_events_type_columns() {
        List<Column> columns = List.of(
              Column.from(AvailableColumn.EVENT_TYPE_ID),
              Column.from(AvailableColumn.EVENT_TYPE__),
              Column.from(AvailableColumn.EVENT_TYPE_DESCRIPTION)
        );

        String expectedColumns = " (id, event_type, description)";
        String expectedParamFields = " ((?::varchar), (?::event_type), (?::text))";

        String actualColumns = ColumnAliasQueryFactory.makeSubInsertQuery(columns).toString();
        String actualParamFields = ColumnAliasQueryFactory.makeSubInsertValuesFieldQuery(columns).toString();

        assertEquals(expectedColumns, actualColumns);
        assertEquals(expectedParamFields, actualParamFields);
    }

    @Test
    void makeSubInsertQuery_for_events_category_columns() {
        List<Column> columns = List.of(
                Column.from(AvailableColumn.EVENT_CATEGORY_ID),
                Column.from(AvailableColumn.EVENT_CATEGORY__),
                Column.from(AvailableColumn.EVENT_CATEGORY_DESCRIPTION)
        );

        String expectedColumns = " (id, event_category, description)";
        String expectedParamFields = " ((?::varchar), (?::event_category), (?::text))";

        String actualColumns = ColumnAliasQueryFactory.makeSubInsertQuery(columns).toString();
        String actualParamFields = ColumnAliasQueryFactory.makeSubInsertValuesFieldQuery(columns).toString();

        assertEquals(expectedColumns, actualColumns);
        assertEquals(expectedParamFields, actualParamFields);
    }
}