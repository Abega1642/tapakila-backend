package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.QueryFilter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Query {
    private TableName tableName;
    private List<Column> columns;
    private List<InnerJoinQuery> innerJoins;
    private List<QueryFilter> queryFilters;
    private List<Criteria> criteria;

    public Query(
            TableName tableName,
            List<Column> columns,
            List<InnerJoinQuery> innerJoins,
            List<QueryFilter> queryFilters,
            List<Criteria> criteria
    ) {
        this.tableName = tableName;
        this.columns = columns;
        this.innerJoins = innerJoins;
        this.queryFilters = queryFilters;
        this.criteria = criteria;
    }

    public static class Builder {
        private TableName tableName;
        private List<Column> columns;
        private List<InnerJoinQuery> innerJoins;
        private List<QueryFilter> queryFilters;
        private List<Criteria> criteria;

        public Builder() {
            this.tableName = null;
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

        StringBuilder cols = colFactory.makeQuery(columns);
        StringBuilder innerJoinsQuery = ijFactory.makeQuery(innerJoins);
        StringBuilder qrFilters = qfqFactory.makeQuery(queryFilters);
        StringBuilder fQuery = fqFactory.makeQuery(CriteriaSeparator.extractFilters(criteria));
        StringBuilder oQuery = oqFactory.makeQuery(CriteriaSeparator.extractOrders(criteria));


        return new StringBuilder("SELECT ")
                .append(cols)
                .append(" FROM ")
                .append(tableName.getValue())
                .append(" ")
                .append(innerJoinsQuery)
                .append("WHERE 1=1")
                .append(qrFilters)
                .append(fQuery)
                .append(oQuery);
    }
}
