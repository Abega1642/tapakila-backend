package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.PaymentMode;
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
public class PaymentModeDao implements DAO<PaymentMode>{
    private final DataSource dataSource;

    @Override
    public PaymentMode save(PaymentMode entity) {
        throw new NotImplementedException("Saving payement mode not implemented yet");
    }

    @Override
    public Optional<PaymentMode> findById(String id) {
        throw new NotImplementedException("Finding payment mode by id not implemented yet");
    }

    @Override
    public List<PaymentMode> findAll(long page, long size) {
        throw new NotImplementedException("Finding all payment modes not implemented yet");
    }

    @Override
    public List<PaymentMode> findAllByCriteria(List<Criteria> criteria, long page, long size) {
        throw new NotImplementedException("Finding all payment modes by criteria not implemented yet");
    }

    @Override
    public List<PaymentMode> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences) {
        throw new NotImplementedException("Updating payment mode not implemented yet");
    }

    @Override
    public Optional<PaymentMode> delete(String id) {
        throw new NotImplementedException("Deleting payment mode not implement yet");
    }
}
