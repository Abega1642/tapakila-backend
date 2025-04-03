package dev.razafindratelo.tapakilaBackend.mapper;

import dev.razafindratelo.tapakilaBackend.dto.UpdatePayload;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;

public class ColumnMapper {
    public static Column mapToColumn(UpdatePayload updatePayload) {
        return new Column(updatePayload.attributeToBeUpdate(), updatePayload.updatedValue().toString());
    }
}
