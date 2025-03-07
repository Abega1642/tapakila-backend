package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
    ENTERTAINMENT,
    MUSIC,
    SPORTS,
    THEATER_AND_SHOWS,
    EDUCATION,
    CINEMA,
    EXHIBITIONS_AND_CULTURE,
    FESTIVALS_AND_FAIRS,
    PARTIES,
    GAMING,
    BUSINESS,
    RELIGIOUS,
    FOOD_AND_DRINK,
    CHARITY,
    TRAVEL,
    FASHION,
    TECHNOLOGY,
    ENVIRONMENTAL,
    FAMILY_AND_KIDS,
    HEALTH_AND_WELLNESS,
    LITERATURE_AND_BOOKS,
    COMEDY,
    NIGHTLIFE,
    OUTDOOR_AND_ADVENTURE,
    COMMUNITY_AND_SOCIAL,
    HISTORY_AND_HERITAGE,
    SCIENCE_AND_TECH,
    ANIMALS_AND_NATURE; 

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
