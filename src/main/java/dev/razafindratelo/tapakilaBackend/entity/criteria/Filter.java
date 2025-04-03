package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.dto.FilterDto;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.BooleanOperator;
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
    private BooleanOperator booleanOperator = BooleanOperator.AND;
    private OperatorType operatorType;
    private Object value;

    public Filter(BooleanOperator booleanOperator, AvailableColumn column, OperatorType operatorType, Object value) {
        super(column);
        this.booleanOperator = booleanOperator;
        this.value = value;
        this.operatorType = operatorType;
    }

    public Filter(AvailableColumn column, OperatorType operatorType, Object value) {
        super(column);
        this.value = value;
        this.operatorType = operatorType;
    }

    public static Filter of(FilterDto filterDto) {
        return new Filter(
                filterDto.getBoolOperator(),
                filterDto.getAttribute(),
                filterDto.getOperator(),
                filterDto.getValue()
        );
    }

}
