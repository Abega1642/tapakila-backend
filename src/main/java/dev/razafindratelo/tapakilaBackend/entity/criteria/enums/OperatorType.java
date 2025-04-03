package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum OperatorType {
    EQUAL("="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    NOT_EQUAL("<>"),

    IN("IN"),
    CONTAINS("ILIKE"),
    BETWEEN("BETWEEN");

    private final String representation;

    OperatorType(String representation) {
        this.representation = representation;
    }

    @JsonCreator
    public static OperatorType fromString(String value) {
        for (OperatorType type : OperatorType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
