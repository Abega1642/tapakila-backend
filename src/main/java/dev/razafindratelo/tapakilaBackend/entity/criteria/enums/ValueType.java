package dev.razafindratelo.tapakilaBackend.entity.criteria.enums;

import lombok.Getter;

@Getter
public enum ValueType {
    STRING("varchar"),
    INTEGER("int"),
    LONG("int8"),
    DATE("date"),
    FLOAT("float"),
    TIMESTAMP("timestamp"),
    BOOLEAN("boolean"),
    TEXT("text"),
    REQUEST("request");

    private final String representation;

    ValueType(String representation) {
        this.representation = representation;
    }
}
