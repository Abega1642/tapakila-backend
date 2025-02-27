package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum OperatorType {
    BETWEEN("BETWEEN"),
    EQUAL("="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    NOT_EQUAL("<>"),

    ILIKE_CONTAINS("ILIKE"),
    ILIKE_BEGINS_WITH("ILIKE"),
    ILIKE_ENDS_WITH("ILIKE");

    private final String representation;

    OperatorType(String representation) {
        this.representation = representation;
    }
}
