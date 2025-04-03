package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventStatus {
    DRAFT,
    PUBLISHED,
    SCHEDULED,
    ONGOING,
    COMPLETED,
    CANCELED;

    @JsonCreator
    public static EventStatus fromString(String value) {
        for (EventStatus eventStatus : EventStatus.values()) {
            if (eventStatus.name().equalsIgnoreCase(value)) {
                return eventStatus;
            }
        }
        return null;
    }
}
