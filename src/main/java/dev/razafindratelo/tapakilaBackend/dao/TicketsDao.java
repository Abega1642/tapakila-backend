package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
public class TicketsDao implements DAO<Tickets> {
    private final DataSource dataSource;

    @Override
    public Tickets save(Tickets entity) {
        throw new NotImplementedException("Saving ticket not implemented yet");
    }

    @Override
    public Optional<Tickets> findById(String id) {
        throw new NotImplementedException("Finding ticket by id not implemented yet");
    }

    @Override
    public List<Tickets> findAll(long page, long size) {
        throw new NotImplementedException("Finding all tickets not implemented yet");
    }

    @Override
    public List<Tickets> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding all tickets with criteria not implemented yet");
    }

    @Override
    public List<Tickets> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("Updating ticket not implemented yet");
    }

    @Override
    public Optional<Tickets> delete(String id) {
        throw new NotImplementedException("Deleting ticket not implemented yet");
    }
}
