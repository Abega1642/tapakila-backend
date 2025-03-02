package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ColumnAliasQueryFactory implements SubQueryFactory<Column> {

    @Override
    public StringBuilder makeSubSelectQuery(List<Column> columns) {
        StringBuilder query = new StringBuilder();
        for (var col : columns) {
            if (col.getColumn().getValue().equals(col.getAlias()) || col.getColumn().getValue().equals("REQUEST")) {
                query.append(col.getAlias()).append(", ");
            } else {
                query.append(col.getColumn().getValue()).append(" AS ").append(col.getAlias()).append(", ");
            }
        }
        return new StringBuilder(query.substring(0, query.length() - 2));
    }

    public static StringBuilder makeSubInsertQuery(List<Column> columns) {
        if (columns.isEmpty()) {
            return new StringBuilder();
        }
        StringJoiner joiner = new StringJoiner(", ", " (", ")");
        for (var col : columns) {
            String columnName = col.getColumn().getValue().split("\\.")[1];
            joiner.add(columnName);
        }
        return new StringBuilder(joiner.toString());
    }

}
