package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;

public enum BooleanOperator {
    OR, AND;

    @JsonCreator
    public static BooleanOperator fromString(String value) {
        for (BooleanOperator type : BooleanOperator.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
