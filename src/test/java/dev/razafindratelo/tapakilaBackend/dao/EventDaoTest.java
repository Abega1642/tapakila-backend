package dev.razafindratelo.tapakilaBackend.dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.razafindratelo.tapakilaBackend.config.ObjectMapperConfig;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.EventCategoryDetail;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Order;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OrderType;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventCategory;
import dev.razafindratelo.tapakilaBackend.entity.enums.EventType;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import dev.razafindratelo.tapakilaBackend.mapper.EventMapper;
import dev.razafindratelo.tapakilaBackend.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EventDaoTest {
    /*
	private EventDao subject;


    @BeforeEach
    void setUp() {
        var ds = new DataSource();
        ObjectMapper objectMapper = new ObjectMapperConfig().objectMapper();
        subject = new EventDao(ds, new EventMapper(objectMapper, new UserMapper(objectMapper)));
    }

    @BeforeEach
    void afterAll() {
        List<Column> cols = List.of (new Column(AvailableColumn.EVENT_DESCRIPTION, "Join us for the biggest music festival of the year! Featuring top artists from around the world."));
        List<Filter> refs = List.of(new Filter(AvailableColumn.EVENT_ID, OperatorType.EQUAL,"$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd"));
        subject.update(cols, refs);
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
                "__sorry_but_you_cannot_have_a_look_at_the_user_password__",
                UserRole.ADMIN,
                true,
                LocalDateTime.of(2025, 3, 7, 16, 28, 50, 306472000),
                List.of(
                    new EventCategoryDetail(
                            "$EvC-250ea1b3-6402-4113-9db8-ba4a77c900b9",
                            EventCategory.NIGHTCLUB_PARTY,
                            "Parties held in nightclubs with music and dancing."
                    ),
                    new EventCategoryDetail(
                            "$EvC-6a4df93a-cb21-446d-92f8-fa394eb215a1",
                            EventCategory.MUSIC_FESTIVAL,
                            "A large-scale event featuring multiple music acts and performances."
                    )
                )
        );
        Event actual = subject.findById(expectedId).orElseThrow();
        log.info(actual.toString());
        Set<EventType> actualTypes = actual.getEventTypeDetail()
                        .stream().map(EventTypeDetail::getEventType).collect(Collectors.toSet());

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
    void findAll_without_criteria_size4_page1() {
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

    @Test
    void findAll_without_criteria_size2_page1() {
        List<String> expectedIds = List.of(
                "$Evt-5be48f4c-790a-4206-835f-ba39210c54f7",
                "$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd"
        );
        List<Event> actual = subject.findAll(1, 2);
        actual.forEach(e -> log.info(e.toString()));

        List<String> actualIds = actual.stream().map(Event::getId).toList();


        assertEquals(2, actual.size());
        assertTrue(actualIds.containsAll(expectedIds));
    }

    @Test
    void find_all_where_category_just_like_music() {
        List<String> expectedIds = List.of(
                "$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd",
                "$Evt-5be48f4c-790a-4206-835f-ba39210c54f7"
        );
        List<String> expectedOrganizers = List.of(
                "Music Fest Organizers",
                "Studio Showcase"
        );
        List<String> expectedTitles = List.of(
                "Annual Summer Music Festival",
                "Annual Studio Showcase"
        );
        List<String> expectedDescriptions = List.of(
                "Join us for the biggest music festival of the year! Featuring top artists from around the world.",
                "A night filled with mesmerizing performances, exquisite music, and a live orchestra. Join us for an unforgettable evening!"
        );
        List<String> expectedCategories = List.of(
                "MUSIC_FESTIVAL",
                "OPERA"
        );

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "music")
        );

        List<Event> actual = subject.findAllByCriteria(criteria, 1, 4);
        List<String> actualIds = actual.stream().map(Event::getId).toList();
        List<String> actualTitles = actual.stream().map(Event::getTitle).toList();
        List<String> actualDescriptions = actual.stream().map(Event::getDescription).toList();
        List<String> actualCategories = actual.stream().map(e -> e.getCategory().getEventCategory().toString())
                .toList();
        actual.forEach(e -> log.info(e.toString()));
        assertEquals(2, actual.size());
        assertTrue(actualIds.containsAll(expectedIds));
        assertTrue(actualTitles.containsAll(expectedTitles));
        assertTrue(actualDescriptions.containsAll(expectedDescriptions));
        assertTrue(actualCategories.containsAll(expectedCategories));

    }

    @Test
    void find_all_where_category_just_like_party() {
        String expectedId = "$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2";
        String expectedOrganizes = "Farthotel";
        String expectedTitle = "Night Party";
        String expectedDescription =
                "Join us for an unforgettable night party at Hotel Carlton! Featuring special performances and a star-studded guest list.";
        String expectedCategory = "NIGHTCLUB_PARTY";

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.EVENT_DESCRIPTION, OperatorType.CONTAINS, "party"),

                new Order(AvailableColumn.EVENT_DATE_TIME, OrderType.DESC)
        );

        List<Event> actual = subject.findAllByCriteria(criteria, 1, 4);
        List<String> actualIds = actual.stream().map(Event::getId).toList();
        List<String> actualTitles = actual.stream().map(Event::getTitle).toList();
        List<String> actualDescriptions = actual.stream().map(Event::getDescription).toList();
        List<String> actualCategories = actual.stream().map(e -> e.getCategory().getEventCategory().toString())
                .toList();

        actual.forEach(e -> log.info(e.toString()));
        assertEquals(1, actual.size());
        assertTrue(actualIds.contains(expectedId));
        assertTrue(actualTitles.contains(expectedTitle));
        assertTrue(actualDescriptions.contains(expectedDescription));
        assertTrue(actualCategories.contains(expectedCategory));

    }

    @Test
    void find_all_where_categories_are_equals_azerty_expect_to_get_error() {

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.EVENT_CATEGORY, OperatorType.EQUAL, "AZERTY")
        );

        assertThrows(RuntimeException.class, () -> subject.findAllByCriteria(criteria, 1, 4));

    }

    @Test
    void test_update_method() {
        List<Column> cols = List.of (new Column(AvailableColumn.EVENT_DESCRIPTION, "This is a random test to update event description"));
        List<Filter> refs = List.of(new Filter(AvailableColumn.EVENT_ID, OperatorType.EQUAL,"$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd"));
        List<Event> actual = subject.update(cols, refs);

        assertEquals(1, actual.size());
        assertEquals("This is a random test to update event description", actual.getFirst().getDescription());
    }	
	*/
}
