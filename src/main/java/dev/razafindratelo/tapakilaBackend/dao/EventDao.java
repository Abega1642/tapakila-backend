package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Getter
public class EventDao implements DAO<Event> {
    private final DataSource dataSource;

    @Override
    public Event save(Event entity) {
        throw new NotImplementedException("Saving event not implemented yet");
    }

    @Override
    public Optional<Event> findById(String id) {
        throw new NotImplementedException("Finding event by id not implemented yet");
    }

    @Override
    public List<Event> findAll(long page, long size) {
        throw new NotImplementedException("Finding all events not implemented yet");
    }

    @Override
    public List<Event> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding all events with criteria not implemented yet");
    }

    @Override
    public Event update(String id, Event entity) {
        throw new NotImplementedException("Updating event not implemented yet");
    }

    @Override
    public Optional<Event> delete(String id) {
        throw new NotImplementedException("Deleting event not implemented yet");
    }
}
