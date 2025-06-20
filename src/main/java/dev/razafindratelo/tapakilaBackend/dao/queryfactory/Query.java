package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.*;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;
import lombok.Data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Data
public class Query {
    private TableName tableName;
    private List<Column> columns;
    private List<InnerJoinQuery> innerJoins;
    private List<QueryFilter> queryFilters;
    private List<Criteria> criteria;
    private GroupBy groupBy;
    private StringBuilder query;

    private Query(
            TableName tableName,
            List<Column> columns,
            List<InnerJoinQuery> innerJoins,
            List<QueryFilter> queryFilters,
            GroupBy groupBy,
            List<Criteria> criteria
    ) {
        this.tableName = tableName;
        this.columns = columns;
        this.innerJoins = innerJoins;
        this.queryFilters = queryFilters;
        this.groupBy = groupBy;
        this.criteria = criteria;
    }

    public static class Builder {
        private TableName tableName;
        private List<Column> columns;
        private List<InnerJoinQuery> innerJoins;
        private List<QueryFilter> queryFilters;
        private GroupBy groupBy;
        private List<Criteria> criteria;

        public Builder() {
            this.tableName = null;
            this.groupBy = new GroupBy(List.of());
            this.columns = new ArrayList<>();
            this.innerJoins = new ArrayList<>();
            this.queryFilters = new ArrayList<>();
            this.criteria = new ArrayList<>();
        }

        public Builder tableName(TableName tableName) {
            this.tableName = tableName;
            return this;
        }
        public Builder column(List<Column> columns) {
            this.columns = columns;
            return this;
        }
        public Builder innerJoin(List<InnerJoinQuery> innerJoins) {
            this.innerJoins = innerJoins;
            return this;
        }
        public Builder queryFilter(List<QueryFilter> queryFilters) {
            this.queryFilters = queryFilters;
            return this;
        }
        public Builder groupBy(GroupBy groupBy) {
            this.groupBy = groupBy;
            return this;
        }
        public Builder criteria(List<Criteria> criteria) {
            this.criteria = criteria;
            return this;
        }
        public Query build() {
            return new Query(
                    this.tableName,
                    this.columns,
                    this.innerJoins,
                    this.queryFilters,
                    this.groupBy,
                    this.criteria
            );
        }
    }

    public StringBuilder getSelectQuery() {
        ColumnAliasQueryFactory colFactory = new ColumnAliasQueryFactory();
        FilterQueryFactory fqFactory = new FilterQueryFactory();
        QueryFilterQueryFactory qfqFactory = new QueryFilterQueryFactory();
        OrderQueryFactory oqFactory = new OrderQueryFactory();
        InnerJoinQueryFactory ijFactory = new InnerJoinQueryFactory();

        StringBuilder cols = colFactory.makeSubSelectQuery(columns);
        StringBuilder innerJoinsQuery = ijFactory.makeSubSelectQuery(innerJoins);
        StringBuilder qrFilters = qfqFactory.makeSubSelectQuery(queryFilters);
        StringBuilder fQuery = fqFactory.makeSubSelectQuery(CriteriaSeparator.extractFilters(criteria));
        StringBuilder oQuery = oqFactory.makeSubSelectQuery(CriteriaSeparator.extractOrders(criteria));


        this.query = new StringBuilder("SELECT ")
                                    .append(cols)
                                    .append(" FROM ")
                                    .append(tableName.getValue())
                                    .append(" ")
                                    .append(innerJoinsQuery)
                                    .append("WHERE 1=1")
                                    .append(qrFilters)
                                    .append(fQuery)
                                    .append(GroupByFactory.makeQuery(groupBy))
                                    .append(oQuery);
        return this.query;
    }

    public String getWhereClause() {
        FilterQueryFactory fqFactory = new FilterQueryFactory();
        StringBuilder fQuery = fqFactory.makeSubSelectQuery(CriteriaSeparator.extractFilters(criteria));

        return """
                    WHERE 1=1
               """ + fQuery.toString();
    }

    public StringBuilder getInsertQuery() {

        return new StringBuilder("INSERT INTO ")
                .append(tableName.getValue().split(" ")[0])
                .append(ColumnAliasQueryFactory.makeSubInsertQuery(columns))
                .append(" VALUES")
                .append(ColumnAliasQueryFactory.makeSubInsertValuesFieldQuery(columns));
    }

    public StringBuilder getUpdateQuery(List<Filter> updatedColumnsReferences) {

        StringBuilder updateRefs = FilterQueryFactory.makeSubUpdateSelectQuery(updatedColumnsReferences);

        return new StringBuilder("UPDATE ")
                .append(tableName.getValue().split(" ")[0])
                .append(ColumnAliasQueryFactory.makeSubUpdateQuery(columns))
                .append(" WHERE 1=1")
                .append(updateRefs);
    }

    public int completeQueryAndReturnLastParamIndex(PreparedStatement statement, int lastParam) throws SQLException {
        List<Filter> filters = CriteriaSeparator.extractFilters(criteria);
        int parameterIndex = lastParam;

        for (Filter f : filters) {
            if (!f.getColumnName().getValueType().equals(ValueType.REQUEST)) {
                parameterIndex++;

                if (f.getOperatorType().equals(OperatorType.BETWEEN)) {
                    List<Object> values = (List<Object>) f.getValue();

                    statement.setObject(parameterIndex, values.getFirst());
					parameterIndex++;

                    statement.setObject(parameterIndex, values.getLast());

                } else {
                    statement.setObject(parameterIndex, f.getValue());
                }
            }

        }

        return parameterIndex;
    }
}
