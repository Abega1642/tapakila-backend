package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum OperatorType {
    EQUAL("="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    NOT_EQUAL("<>"),

    CONTAINS("ILIKE"),
    BETWEEN("BETWEEN");

    private final String representation;

    OperatorType(String representation) {
        this.representation = representation;
    }
}
