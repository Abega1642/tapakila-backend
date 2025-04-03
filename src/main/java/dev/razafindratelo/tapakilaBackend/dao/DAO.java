package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T entity);
    Optional<T> findById(String id);
    List<T> findAll(long page, long size);
    List<T> findAllByCriteria(List<Criteria> criteria, long page, long size);

    /**
     * {@code update} method has objectif to update any entity {@code T} with specified columns to be updated
     * and specific way of telling which resource to update.
     * @param columnsToBeUpdated : This is the list of all columns to be updated
     * @param updateColumnReferences : This is the list of the filter criteria we want to make our update with
     * @return The return statement is the list of all resources affected according to the given criteria {@code updateColumnReferences}
     */
    List<T> update(List<Column> columnsToBeUpdated, List<Filter> updateColumnReferences);

    Optional<T> delete(String id);
}
