package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.config.ObjectMapperConfig;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import dev.razafindratelo.tapakilaBackend.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserDaoTest {
    private UserDao subject;

    @BeforeEach
    void setUp() {
        subject = new UserDao(new DataSource(), new UserMapper(new ObjectMapperConfig().objectMapper()));
    }

    /**
     * This test is a success. That's why I disable it
     */
    @Test
    @Disabled
    void save() {
        User expected = new User(
                "a.razafindratelo@gmail.com",
                "Razafindratelo",
                "Abegaa",
                "/resources/a_razafindratelo/no_image.png",
                "password",
                UserRole.USER,
                false,
                LocalDateTime.now(),
                List.of()
        );
        var actual = subject.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        User expected = new User(
                "a.razafindratelo@gmail.com",
                "Razafindratelo",
                "Abegà",
                "/resources/a_razafindratelo/no_image.png",
                "__sorry_but_you_cannot_have_a_look_at_the_user_password__",
                UserRole.USER,
                false,
                LocalDateTime.of(2025, 3, 7, 16, 34, 17, 78055000),
                List.of()
        );

        User actual = subject.findById("a.razafindratelo@gmail.com").orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        List<String> expectedEmails = List.of(
                "admin@example.com",
                "a.razafindratelo@gmail.com"
        );
        List<User> actual = subject.findAll(1, 5);
        log.info(actual.toString());
        List<String> actualEmails = actual.stream().map(User::getEmail).toList();

        assertEquals(expectedEmails, actualEmails);
    }

    @Test
    void update() {
        List<Column> columns = List.of(
                new Column(AvailableColumn.USER_FIRST_NAME, "Abegà")
        );
        List<Filter> filters = List.of(
                new Filter (AvailableColumn.USER_EMAIL, OperatorType.EQUAL, "a.razafindratelo@gmail.com")
        );

        User actual = subject.update(columns, filters).getFirst();
    }
}