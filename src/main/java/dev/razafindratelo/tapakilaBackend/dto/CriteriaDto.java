package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.TableName;
import dev.razafindratelo.tapakilaBackend.mapper.AttributeMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class CriteriaDto {
    private TableName resource;
    private AvailableColumn attribut;

    public CriteriaDto(String resource, String attribut) {
        this.attribut = AttributeMapper.getTableNameFromTableName(attribut);
        this.resource = TableName.valueOf(resource);
    }
}
