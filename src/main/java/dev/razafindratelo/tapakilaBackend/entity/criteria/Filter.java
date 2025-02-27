package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.ValueType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Filter extends Criteria {
    private OperatorType operatorType;
    private Object value;
    private ValueType valueSQLType;

    public Filter(AvailableColumn column, OperatorType operatorType, Object value, ValueType valueSQLType) {
        super(column);
        this.value = value;
        this.valueSQLType = valueSQLType;
        this.operatorType = operatorType;
    }
}
