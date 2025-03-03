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
                    joiner.add("AND")
                            .add(filter.getColumnName().getValue())
                            .add(filter.getOperatorType().getRepresentation())
                            .add("'%' || ? || '%'");
                    break;
                case BETWEEN:
                    joiner.add("AND")
                            .add(filter.getColumnName().getValue())
                            .add(filter.getOperatorType().getRepresentation())
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")")
                            .add("AND")
                            .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    break;
                default:
                    if (filter.getColumnName().getValueType().equals(ValueType.REQUEST)) {
                        joiner.add("AND")
                                .add(filter.getColumnName().getValue())
                                .add(filter.getOperatorType().getRepresentation())
                                .add(filter.getValue().toString());
                    } else {
                        joiner.add("AND")
                                .add(filter.getColumnName().getValue())
                                .add(filter.getOperatorType().getRepresentation())
                                .add("(?::" + filter.getColumnName().getValueType().getRepresentation() + ")");
                    }
            }
        }
        return new StringBuilder(joiner.toString());
    }
}
