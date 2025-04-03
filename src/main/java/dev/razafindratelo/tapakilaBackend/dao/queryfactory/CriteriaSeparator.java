package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Order;
import java.util.List;
import java.util.Objects;


public class CriteriaSeparator {
    public static List<Filter> extractFilters(List<Criteria> criteria) {
       return criteria.stream().map(
                c -> (c instanceof Filter f) ? f : null
        ).filter(Objects::nonNull).toList();
    }

    public static List<Order> extractOrders(List<Criteria> criteria) {
        return criteria.stream().map(
                c -> (c instanceof Order o) ? o : null
        ).filter(Objects::nonNull).toList();
    }

}
