package dev.razafindratelo.tapakilaBackend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T mapFrom(ResultSet rs) throws SQLException;
}
