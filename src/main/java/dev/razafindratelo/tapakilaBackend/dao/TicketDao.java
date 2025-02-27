package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.Ticket;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@AllArgsConstructor
public class TicketDao implements DAO<Ticket> {
    private final DataSource dataSource;

    @Override
    public Ticket save(Ticket entity) {
        throw new NotImplementedException("Saving ticket type not implemented yet");
    }

    @Override
    public Optional<Ticket> findById(String id) {
        throw new NotImplementedException("Finding ticket type by id not implemented yet");
    }

    @Override
    public List<Ticket> findAll(long page, long size) {
        throw new NotImplementedException("Finding tickets type not implemented yet");
    }

    @Override
    public List<Ticket> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding tickets type by criteria not implemented yet");
    }

    @Override
    public Ticket update(String id, Ticket entity) {
        throw new NotImplementedException("Updating ticket type not implemented yet");
    }

    @Override
    public Optional<Ticket> delete(String id) {
        throw new NotImplementedException("Deleting ticket type not implemented yet");
    }
}
