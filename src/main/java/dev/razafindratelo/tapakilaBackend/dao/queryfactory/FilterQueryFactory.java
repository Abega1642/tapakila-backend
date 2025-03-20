package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;

import java.util.List;
import java.util.StringJoiner;

public class FilterQueryFactory implements SubQueryFactory<Filter> {
    @Override
    public StringBuilder makeSubSelectQuery(List<Filter> filters) {
        StringJoiner joiner = new StringJoiner(" ", " ", "");

        if (filters.isEmpty()) {
            return new StringBuilder();
        }
        for (Filter filter : filters) {
            switch (filter.getOperatorType()) {
                case CONTAINS:
                    joiner.add(filter.getBooleanOperator().toString())
                            .add(filter.getColumnName().getValue())
                            .add(filter.getOperatorType().getRepresentation())
                            .add("'%' || ? || '%'");
                    break;
                case BETWEEN:
                    joiner.add(filter.getBooleanOperator().toString())
                            .add(filter.getColumnName().getValue())
                            .add(filter.getOperatorType().getRepresentation())
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")")
                            .add("AND")
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    break;
                default:
                    if (filter.getColumnName().getValueType().equals(ValueType.REQUEST)) {
                        joiner.add(filter.getBooleanOperator().toString())
                                .add(filter.getColumnName().getValue())
                                .add(filter.getOperatorType().getRepresentation())
                                .add(filter.getValue().toString());
                    } else {
                        joiner.add(filter.getBooleanOperator().toString())
                                .add(filter.getColumnName().getValue())
                                .add(filter.getOperatorType().getRepresentation())
                                .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    }
            }
        }
        return new StringBuilder(joiner.toString());
    }

    public static StringBuilder makeSubUpdateSelectQuery(List<Filter> filters) {
        StringJoiner joiner = new StringJoiner(" ", " ", "");

        if (filters.isEmpty()) {
            return new StringBuilder();
        }
        for (Filter filter : filters) {
            String columnName = filter.getColumnName().getValue().split("\\.")[1];
            switch (filter.getOperatorType()) {
                case CONTAINS:
                    joiner.add(filter.getBooleanOperator().toString())
                            .add(columnName)
                            .add(filter.getOperatorType().getRepresentation())
                            .add("'%' || ? || '%'");
                    break;
                case BETWEEN:
                    joiner.add(filter.getBooleanOperator().toString())
                            .add(columnName)
                            .add(filter.getOperatorType().getRepresentation())
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")")
                            .add("AND")
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    break;
                default:
                    if (filter.getColumnName().getValueType().equals(ValueType.REQUEST)) {
                        joiner.add(filter.getBooleanOperator().toString())
                                .add(columnName)
                                .add(filter.getOperatorType().getRepresentation())
                                .add(filter.getValue().toString());
                    } else {
                        joiner.add(filter.getBooleanOperator().toString())
                                .add(columnName)
                                .add(filter.getOperatorType().getRepresentation())
                                .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    }
            }
        }
        return new StringBuilder(joiner.toString());
    }
}
