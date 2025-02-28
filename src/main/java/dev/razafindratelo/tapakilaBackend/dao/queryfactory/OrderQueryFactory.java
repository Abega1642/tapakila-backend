package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Order;
import java.util.List;
import java.util.StringJoiner;

public class OrderQueryFactory implements SubQueryFactory<Order> {

    @Override
    public StringBuilder makeQuery(List<Order> orders) {
        StringJoiner joiner = new StringJoiner(", ", " ORDER BY ", "");

        if (orders.isEmpty()) {
            return new StringBuilder();
        }
        for (Order order : orders) {
            joiner.add(order.getColumnName().getValue() + " " + order.getType());
        }
        return new StringBuilder(joiner.toString());
    }
}
