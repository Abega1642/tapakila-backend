package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.config.ObjectMapperConfig;
import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.enums.UserRole;
import dev.razafindratelo.tapakilaBackend.mapper.AccountActivationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountActivationDaoTest {
    private AccountActivationDao subject;

    @BeforeEach
    void setUp() {
        subject = new AccountActivationDao(
                new DataSource(),
                new AccountActivationMapper(new ObjectMapperConfig().objectMapper())
        );
    }

    @Test
    void save() {
        User expectedUser =   new User(
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
        AccountActivation expected = new AccountActivation(
                "$AcA-" + UUID.randomUUID(),
                expectedUser,
                "000000001",
                null,
                null,
                null,
                false
        );

        AccountActivation actual = subject.save(expected);

        assertEquals(expected, actual);

    }

    @Test
    void findById() {
        String expectedId = "$AcA-c5f47e0a-3aec-4ee5-ae38-80c052bb3a19";
        User expectedUser =   new User(
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
        AccountActivation expected = new AccountActivation(
                expectedId,
                expectedUser,
                "000000001",
                LocalDateTime.of(2025, 3, 8, 12, 7, 59, 316314000),
                LocalDateTime.of(2025, 3, 8, 12, 17, 59, 316314000),
                null,
                true
        );
        AccountActivation actual = subject.findById(expectedId).orElseThrow();

        assertEquals(expected, actual);

    }

    @Test
    void findByUserEmail() {
        User expectedUser =   new User(
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
        AccountActivation expected = new AccountActivation(
                "$AcA-c5f47e0a-3aec-4ee5-ae38-80c052bb3a19",
                expectedUser,
                "000000001",
                LocalDateTime.of(2025, 3, 8, 12, 7, 59, 316314000),
                LocalDateTime.of(2025, 3, 8, 12, 17, 59, 316314000),
                LocalDateTime.of(2025, 3, 8, 12, 16, 59, 316314000),
                true
        );

        AccountActivation actual = subject.findByUserEmail("a.razafindratelo@gmail.com").orElseThrow();

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var activatedAt =  LocalDateTime.of(2025, 3, 8, 12, 16, 59, 316314000);
        User expectedUser =   new User(
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
        AccountActivation expected = new AccountActivation(
                "$AcA-c5f47e0a-3aec-4ee5-ae38-80c052bb3a19",
                expectedUser,
                "000000001",
                LocalDateTime.of(2025, 3, 8, 12, 7, 59, 316314000),
                LocalDateTime.of(2025, 3, 8, 12, 17, 59, 316314000),
                activatedAt,
                true
        );
        AccountActivation actual = subject.update(expectedUser.getEmail(), activatedAt).getFirst();

        assertEquals(expected, actual);

    }
}