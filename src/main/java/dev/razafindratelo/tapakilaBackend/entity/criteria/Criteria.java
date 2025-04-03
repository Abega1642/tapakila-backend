package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Criteria {
    private AvailableColumn columnName;
}
