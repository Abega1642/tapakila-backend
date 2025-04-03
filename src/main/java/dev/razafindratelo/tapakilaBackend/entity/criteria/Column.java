package dev.razafindratelo.tapakilaBackend.entity.criteria;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Column{
    private AvailableColumn column;
    private String alias;

    public Column(AvailableColumn column, String alias) {
        this.column = column;
        this.alias = alias;
    }

    public static Column from(AvailableColumn column) {
        return new Column(column, column.getValue());
    }
}
