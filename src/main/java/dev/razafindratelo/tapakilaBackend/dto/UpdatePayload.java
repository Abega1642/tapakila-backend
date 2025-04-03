package dev.razafindratelo.tapakilaBackend.dto;

import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;

public record UpdatePayload(AvailableColumn attributeToBeUpdate, Object updatedValue) {
}
