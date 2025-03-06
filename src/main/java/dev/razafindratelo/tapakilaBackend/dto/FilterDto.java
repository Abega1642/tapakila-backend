package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class FilterDto extends CriteriaDto {
    private OperatorType operator;
    private Object value;

    public FilterDto(String resource, String attribut, String operator, Object value) {
        super(resource, attribut);
        this.operator = OperatorType.valueOf(operator);
        this.value = value;
    }
}
