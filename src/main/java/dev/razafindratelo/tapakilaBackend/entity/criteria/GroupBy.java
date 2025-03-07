package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class GroupBy {
    private List<AvailableColumn> columns;
}
