package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
public class UserDao implements DAO<User> {
    private final DataSource dataSource;

    @Override
    public User save(User entity) {
        throw new NotImplementedException("Saving user not implemented yet");
    }

    @Override
    public Optional<User> findById(String id) {
        throw new NotImplementedException("Finding user by id not implemented yet");
    }

    @Override
    public List<User> findAll(long page, long size) {
        throw new NotImplementedException("Finding all users not implemented yet");
    }

    @Override
    public List<User> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding all users by criteria not implemented yet");
    }

    @Override
    public User update(String id, User entity) {
        throw new NotImplementedException("Updating user not implemented yet");
    }

    @Override
    public Optional<User> delete(String id) {
        throw new NotImplementedException("Deleting user not implemented yet");
    }
}
