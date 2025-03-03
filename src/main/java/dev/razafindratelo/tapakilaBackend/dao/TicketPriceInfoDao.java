package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
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
public class TicketPriceInfoDao implements DAO<TicketPriceInfo> {
    private final DataSource dataSource;

    @Override
    public TicketPriceInfo save(TicketPriceInfo entity) {
        throw new NotImplementedException("Saving ticket price not implemented yet");
    }

    @Override
    public Optional<TicketPriceInfo> findById(String id) {
        throw new NotImplementedException("Finding ticket price by id not implemented yet");
    }

    @Override
    public List<TicketPriceInfo> findAll(long page, long size) {
        throw new NotImplementedException("Finding ticket prices not implemented yet");
    }

    @Override
    public List<TicketPriceInfo> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding ticket prices by criteria not implemented yet");
    }

    @Override
    public List<TicketPriceInfo> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("Updating ticket price not implemented yet");
    }

    @Override
    public Optional<TicketPriceInfo> delete(String id) {
        throw new NotImplementedException("Deleting ticket price not implemented yet");
    }
}
