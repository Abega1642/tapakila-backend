package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;

public class QueryFilter extends Filter {

    public QueryFilter(AvailableColumn column, OperatorType operatorType, Object value) {
        super(column.changeValueType(ValueType.REQUEST), operatorType, value);
    }

}
