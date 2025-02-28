package dev.razafindratelo.tapakilaBackend.dao;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T entity);
    Optional<T> findById(String id);
    List<T> findAll(long page, long size);
    List<T> findAllByCriteria(List<Criteria> criteria, long page, long size);
    T update(String id,T entity);
    Optional<T> delete(String id);
}
