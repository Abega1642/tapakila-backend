package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventsType;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EventDaoTest {
    private EventDao subject;

    @BeforeEach
    void setUp() {
        var ds = new DataSource();
        subject = new EventDao(ds);
    }

    @Test
    void findById() {
        String expectedId =  "$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd";
        String organizer = "Music Fest Organizers";
        String expectedTitle = "Annual Summer Music Festival";
        String expectedDescription =
                "Join us for the biggest music festival of the year! Featuring top artists from around the world.";
        LocalDateTime expectedDateTime =
                LocalDateTime.of(2024, 7, 15, 18, 0, 0);
        EventCategory expectedCategory = EventCategory.MUSIC_FESTIVAL;
        EventType expectedType1 = EventType.MUSIC;
        EventType expectedType2 = EventType.ENTERTAINMENT;
        LocalDateTime expectedCreatedAt =
                LocalDateTime.of(2025, 3, 2, 23, 52, 53, 293997000);

        User expectedCreatedBy = new User(
                "admin@example.com",
                "Doe",
                "John",
                "/user/admin/profile.jpg",
                "__password__",
                UserRole.ADMIN,
                true,
                List.of()
        );
        Event actual = subject.findById(expectedId).orElseThrow();
        log.info(actual.toString());
        Set<EventType> actualTypes = actual.getEventsType()
                        .stream().map(EventsType::getEventType).collect(Collectors.toSet());

        assertEquals(expectedId, actual.getId());
        assertEquals(organizer, actual.getOrganizer());
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedDescription, actual.getDescription());
        assertEquals(expectedDateTime, actual.getDateTime());
        assertEquals(expectedCategory, actual.getCategory().getEventCategory());
        assertTrue(actualTypes.contains(expectedType1));
        assertTrue(actualTypes.contains(expectedType2));
        assertEquals(expectedCreatedAt, actual.getCreatedAt().withSecond(53));
        assertEquals(expectedCreatedAt, actual.getUpdatedAt());
        assertEquals(expectedCreatedBy, actual.getCreatedBy());
    }

    @Test
    void findAll_without_criteria_limit4_page1() {
        List<String> expectedIds = List.of(
                "$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd",
                "$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2",
                "$Evt-5be48f4c-790a-4206-835f-ba39210c54f7",
                "$Evt-d0de138a-6c43-45c5-86f9-4772b8f80acc"
        );
        List<Event> actual = subject.findAll(1, 4);
        actual.forEach(e -> log.info(e.toString()));
        List<String> actualIds = actual.stream().map(Event::getId).toList();


        assertEquals(4, actual.size());
        assertTrue(actualIds.containsAll(expectedIds));
    }
}