package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class EventTypeDetailDaoTest {
    private EventTypeDetailDao subject;
    @BeforeEach
    void setUp() {
        subject = new EventTypeDetailDao(new DataSource());
    }

    @Test
    void findById() {
        // Important: make sure to change this id, because this id is generated randomly when launching the v1_migration.sql
        String expectedId = "$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8";
        EventType expectedType = EventType.COMEDY;
        String expectedDescription = "Events focused on comedy and humor.";

        List<EventCategory> expectedCategories = List.of(
                EventCategory.IMPROV_COMEDY,
                EventCategory.COMEDY_FESTIVAL,
                EventCategory.STAND_UP_COMEDY,
                EventCategory.COMEDY_CLUB_NIGHT,
                EventCategory.SATIRE_SHOW
        );

        EventTypeDetail actual = subject.findById(expectedId).orElseThrow();
        assertEquals(expectedId, actual.getId());
        assertEquals(expectedType, actual.getEventType());
        assertEquals(expectedDescription, actual.getDescription());
        assertTrue(actual.getCorrespondingCategories()
                .stream()
                .map(EventCategoryDetail::getEventCategory)
                .toList()
                .containsAll(expectedCategories));
    }

    @Test
    void findAll_page1_size5() {
        List<EventTypeDetail> actual = subject.findAll(1, 5);
        assertEquals(5, actual.size());
        actual.forEach(e -> assertFalse(e.getCorrespondingCategories().isEmpty()));
    }

    @Test
    void findAllByCriteria() {
        List<EventTypeDetail> actual = subject.findAllByCriteria(List.of(
                new Filter(AvailableColumn.EVENT_TYPE__, OperatorType.EQUAL, "COMEDY")
        ), 1, 5);
        assertEquals(1, actual.size());
        actual.forEach(e -> assertEquals(5, e.getCorrespondingCategories().size()));
    }
}