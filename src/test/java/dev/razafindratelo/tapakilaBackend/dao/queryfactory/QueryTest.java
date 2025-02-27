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
                " FROM \"event\" e WHERE 1=1";

        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_getSelectQuery_with_queryFilter() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?")
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
                " FROM \"event\" e WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?)";

        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }

    @Test
    void test_getSelectQuery_with_queryFilter_with_filters_and_orders() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?")
        );

        List<Column> columns = List.of(
                new Column(AvailableColumn.EVENT_CATEGORY, "category_of_the_event"),
                new Column(AvailableColumn.EVENT_LOCATION, "location_of_the_event"),
                new Column(AvailableColumn.EVENT_DATE_TIME, "d_day_of_the_event")
        );

        List<Criteria> criteria = List.of (
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "some_random_value", ValueType.STRING),
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, EventCategory.CONFERENCE, ValueType.EVENT_CATEGORY),
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
                " FROM \"event\" e WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?)" +
                " AND e.description ILIKE '%' || ? || '%' AND e.category = (?::event_category)" +
                " ORDER BY e.date_time DESC, e.location ASC";
        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }

    @Test
    void test_getSelectQuery_with_innerJoin_and_queryFilter_with_filters_and_orders() {
        List<QueryFilter> queryFilters = List.of(
                new QueryFilter(AvailableColumn.EVENT_DATE_TIME, OperatorType.EQUAL,"SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?")
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
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "some_random_value", ValueType.STRING),
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, EventCategory.CONFERENCE, ValueType.EVENT_CATEGORY),
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
                " (SELECT u.last_name, u.first_name AS organizer)" +
                " FROM \"event\" e "+
                "INNER JOIN creates c ON c.id_event = e.id_event " +
                "INNER JOIN \"user\" u ON c.id_user = u.id_user " +
                "WHERE 1=1 AND" +
                " e.date_time = (SELECT MIN(e.date_time) FROM \"event\" e WHERE e.date_time <= ?)" +
                " AND e.description ILIKE '%' || ? || '%' AND e.category = (?::event_category)" +
                " ORDER BY e.date_time DESC, e.location ASC";
        StringBuilder actual = subject.getSelectQuery();

        assertEquals(expected, actual.toString());

    }
}