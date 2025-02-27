package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.QueryFilter;
import java.util.List;
import java.util.StringJoiner;

public class QueryFilterQueryFactory implements SubQueryFactory<QueryFilter> {

    @Override
    public StringBuilder makeQuery(List<QueryFilter> queryFilters) {
        if (queryFilters.isEmpty()) {
            return new StringBuilder();
        }
        StringJoiner joiner = new StringJoiner("", " ", "");

        for (QueryFilter qf : queryFilters) {
            joiner.add("AND ")
                    .add(qf.getColumnName().getValue())
                    .add(" ")
                    .add(qf.getOperatorType().getRepresentation())
                    .add(" (")
                    .add(qf.getValue().toString())
                    .add(")");
        }

        return new StringBuilder(joiner.toString());
    }
}
