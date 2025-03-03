package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import java.util.List;

public interface SubQueryFactory<T> {
    StringBuilder makeSubSelectQuery(List<T> ts);

}
