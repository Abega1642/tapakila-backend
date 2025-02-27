package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import java.util.List;
import java.util.StringJoiner;

public class InnerJoinQueryFactory implements SubQueryFactory<InnerJoinQuery> {
    @Override
    public StringBuilder makeQuery(List<InnerJoinQuery> innerJoinQueries) {
        StringJoiner joiner = new StringJoiner(" ", "", " ");

        if (innerJoinQueries.isEmpty()) {
            return new StringBuilder();
        }
        for (InnerJoinQuery innerJoinQuery : innerJoinQueries) {
            joiner.add(innerJoinQuery.query());
        }
        return new StringBuilder(joiner.toString());
    }
}
