package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.*;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class QueryTest {

    @Test
    void test_getSelectQuery_with_columns_only() {
        List<Column> columns = List.of(
                new Column(AvailableColumn.EVENT_CATEGORY, "category_of_the_event"),
                new Column(AvailableColumn.EVENT_LOCATION, "location_of_the_event")
        );

        Query subject = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .build();

        String expected = "SELECT " +
                "e.category AS category_of_the_event, e.location AS location_of_the_event" +
                " FROM event e WHERE 1=1";

        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_getSelectQuery_with_queryFilter() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?")
        );
        List<Column> columns = List.of(
                new Column(AvailableColumn.EVENT_CATEGORY, "category_of_the_event"),
                new Column(AvailableColumn.EVENT_LOCATION, "location_of_the_event"),
                new Column(AvailableColumn.EVENT_DATE_TIME, "d_day_of_the_event")
        );
        Query subject = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .queryFilter(queryFilters)
                .build();

        String expected = "SELECT " +
                "e.category AS category_of_the_event, e.location AS location_of_the_event, e.date_time AS d_day_of_the_event" +
                " FROM event e WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?)";

        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }

    @Test
    void test_getSelectQuery_with_queryFilter_with_filters_and_orders() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?")
        );

        List<Column> columns = List.of(
                new Column(AvailableColumn.EVENT_CATEGORY, "category_of_the_event"),
                new Column(AvailableColumn.EVENT_LOCATION, "location_of_the_event"),
                new Column(AvailableColumn.EVENT_DATE_TIME, "d_day_of_the_event")
        );

        List<Criteria> criteria = List.of (
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "some_random_value"),
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, EventCategory.LIVE_CONCERT),
                new Order (AvailableColumn.EVENT_DATE_TIME, OrderType.DESC),
                new Order (AvailableColumn.EVENT_LOCATION, OrderType.ASC)
        );

        Query subject = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .queryFilter(queryFilters)
                .criteria(criteria)
                .build();

        String expected = "SELECT " +
                "e.category AS category_of_the_event, e.location AS location_of_the_event, e.date_time AS d_day_of_the_event" +
                " FROM event e WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?)" +
                " AND e.description ILIKE '%' || ? || '%' AND e.category = (?::event_category)" +
                " ORDER BY e.date_time DESC, e.location ASC";
        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }

    @Test
    void test_getSelectQuery_with_innerJoin_and_queryFilter_with_filters_and_orders() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?")
        );

        List<Column> columns = List.of(
                new Column(AvailableColumn.EVENT_CATEGORY, "category_of_the_event"),
                new Column(AvailableColumn.EVENT_LOCATION, "location_of_the_event"),
                new Column(AvailableColumn.EVENT_DATE_TIME, "d_day_of_the_event"),
                Column.from(AvailableColumn.EVENT_ORGANIZER)
        );
        List<InnerJoinQuery> innerJoinQueries = List.of(
                new InnerJoinQuery("INNER JOIN creates c ON c.id_event = e.id_event"),
                new InnerJoinQuery("INNER JOIN \"user\" u ON c.id_user = u.id_user")
        );

        List<Criteria> criteria = List.of (
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "some_random_value"),
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, EventCategory.LIVE_CONCERT),
                new Order (AvailableColumn.EVENT_DATE_TIME, OrderType.DESC),
                new Order (AvailableColumn.EVENT_LOCATION, OrderType.ASC)
        );

        Query subject = new Query.Builder()
                .tableName(TableName.EVENT)
                .column(columns)
                .innerJoin(innerJoinQueries)
                .queryFilter(queryFilters)
                .criteria(criteria)
                .build();

        String expected = "SELECT " +
                "e.category AS category_of_the_event, e.location AS location_of_the_event, e.date_time AS d_day_of_the_event," +
                " e.organizer" +
                " FROM event e "+
                "INNER JOIN creates c ON c.id_event = e.id_event " +
                "INNER JOIN \"user\" u ON c.id_user = u.id_user " +
                "WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM event e WHERE e.date_time <= ?)" +
                " AND e.description ILIKE '%' || ? || '%' AND e.category = (?::event_category)" +
                " ORDER BY e.date_time DESC, e.location ASC";
        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }


    @Test
    void test_makeInsertQuery_into_event_table() {
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
        String expected = "INSERT INTO event " +
                "(id, organizer, title, description, date_time, time_zone, location, "+
                "location_url, image_path, category, status, number_of_ticket, max_ticket_per_user) " +
                "VALUES ((?::varchar), (?::varchar), (?::varchar), (?::text), (?::timestamp), (?::time_zone), (?::text), (?::text), (?::text), (?::event_category), (?::event_status), (?::int8), (?::int))";

        Query subject = new Query.Builder().tableName(TableName.EVENT).column(columns).build();
        String actual = subject.getInsertQuery().toString();

        assertEquals(expected, actual);
    }

    @Test
    void test_makeInsertQuery_into_event_type_table() {
        List<Column> columns = List.of(
                Column.from(AvailableColumn.EVENT_TYPE_ID),
                Column.from(AvailableColumn.EVENT_CATEGORY),
                Column.from(AvailableColumn.EVENT_DESCRIPTION)
        );

        String expected = "INSERT INTO events_type (id, event_category, description) VALUES ((?::varchar), (?::event_category), (?::text))";

        Query subject = new Query.Builder().tableName(TableName.EVENTS_TYPE).column(columns).build();

        String actual = subject.getInsertQuery().toString();

        assertEquals(expected, actual);
    }

    @Test
    void test_makeUpdateQuery_into_event_table() {
        List<Column> columns = List.of(
                Column.from(AvailableColumn.EVENT_TYPE_ID),
                Column.from(AvailableColumn.EVENT_CATEGORY),
                Column.from(AvailableColumn.EVENT_DESCRIPTION)
        );

        List<Filter> updateReferences = List.of(
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "something"),
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, "some_category_value")
        );

        Query subject = new Query.Builder().tableName(TableName.EVENT).column(columns).build();

        String expected = "UPDATE event SET id = (?::varchar), category = (?::event_category), description = (?::text) "
                + "WHERE 1=1 AND description ILIKE '%' || ? || '%' AND category = (?::event_category)";

        String actual = subject.getUpdateQuery(updateReferences).toString();

        assertEquals(expected, actual);

    }
}