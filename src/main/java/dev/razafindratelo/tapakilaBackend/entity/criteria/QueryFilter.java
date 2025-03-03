package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;

public class QueryFilter extends Filter {

    public QueryFilter(AvailableColumn column, OperatorType operatorType, Object value) {
        super(column, operatorType, value);
    }

    private AvailableColumn getTheCorrectAvailableColumn(AvailableColumn column) {
        if(column.getValueType().toString().equals("REQUEST")) {
            return column;
        }
        return AvailableColumn.valueOf(
                column + "_REQ"
        );
    }

}
