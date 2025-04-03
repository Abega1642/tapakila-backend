package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateHandler {
    public static int executeUpdate(
            List<Column> columnToBeUpdated,
            Query queryMaker,
            PreparedStatement statement
    ) throws SQLException {

            int paramIndex = 1;

            for(Column col : columnToBeUpdated) {
                statement.setObject(paramIndex, col.getAlias());
                paramIndex++;
            }

            paramIndex--;

            queryMaker.completeQueryAndReturnLastParamIndex(statement, paramIndex);
            return statement.executeUpdate();
    }
}
