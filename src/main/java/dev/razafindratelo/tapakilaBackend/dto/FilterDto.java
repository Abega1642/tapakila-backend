package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.BooleanOperator;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.mapper.AttributeMapper;
import lombok.*;
import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto implements Serializable {

    private AvailableColumn attribute = AvailableColumn.EVENT_DESCRIPTION;
    @Setter
    private BooleanOperator boolOperator = BooleanOperator.AND;
    @Setter
    private OperatorType operator = getCorrespondingOperator(this.attribute);
    @Setter
    private Object value;

    public FilterDto(String attribute, Object value, String operator) {
        this.attribute = AttributeMapper.getTableNameFromTableName(attribute);
        this.operator = OperatorType.valueOf(operator);
        this.value = value;
    }

	public FilterDto(String attribute, Object value) {
        this.attribute = AttributeMapper.getTableNameFromTableName(attribute);
        this.operator = getCorrespondingOperator(this.attribute);
        this.value = value;
    }

    public FilterDto(String attribute, String booleanOperator, Object value) {
        this.attribute = AttributeMapper.getTableNameFromTableName(attribute);
        this.boolOperator = BooleanOperator.valueOf(booleanOperator);
        this.operator = getCorrespondingOperator(this.attribute);
        this.value = value;
    }


    public OperatorType getCorrespondingOperator(AvailableColumn availableColumn) {

        return switch (availableColumn) {
            case EVENT_TITLE, EVENT_DESCRIPTION, EVENT_LOCATION -> OperatorType.CONTAINS;
            default -> OperatorType.EQUAL;
        };
    }

    public void setAttribute(String attribute) {
        this.attribute = AttributeMapper.getTableNameFromTableName(attribute);
    }

}
