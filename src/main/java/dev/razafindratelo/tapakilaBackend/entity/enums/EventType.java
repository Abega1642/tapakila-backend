package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
    BUSINESS_PROFESSIONAL,
    ENTERTAINMENT_ARTS,
    SPORTS_OUTDOOR,
    TECH_INNOVATION,
    COMMUNITY_SOCIAL,
    EDUCATION_TRAINING,
    FOOD_DRINKS,
    HEATH_WELLNESS;

    @JsonCreator
    public static EventType fromString(String value) {
        for (EventType type : EventType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
