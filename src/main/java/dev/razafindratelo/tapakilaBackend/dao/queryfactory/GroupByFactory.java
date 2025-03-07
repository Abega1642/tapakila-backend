package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.GroupBy;
import java.util.StringJoiner;

public class GroupByFactory{

    public static StringBuilder makeQuery(GroupBy groupBy) {
        StringJoiner joiner = new StringJoiner(", ", " GROUP BY ", "");

        if (groupBy.getColumns().isEmpty()) {
            return new StringBuilder();
        }
        groupBy.getColumns().forEach(column -> joiner.add(column.getValue()));

        return new StringBuilder(joiner.toString());
    }
}
