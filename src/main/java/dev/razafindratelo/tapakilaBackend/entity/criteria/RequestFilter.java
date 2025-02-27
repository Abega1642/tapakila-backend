package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;

public class RequestFilter extends Filter {

    public RequestFilter(AvailableColumn column, OperatorType operatorType, Object value) {
        super(column, operatorType, value, ValueType.REQUEST);
    }

}
