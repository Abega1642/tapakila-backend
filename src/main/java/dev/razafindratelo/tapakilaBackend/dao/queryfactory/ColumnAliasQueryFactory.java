package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import java.util.List;
public class ColumnAliasQueryFactory implements SubQueryFactory<Column> {

    @Override
    public StringBuilder makeQuery(List<Column> columns) {
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
}
