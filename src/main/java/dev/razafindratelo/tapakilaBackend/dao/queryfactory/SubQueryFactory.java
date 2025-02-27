package dev.razafindratelo.tapakilaBackend.dao.queryfactory;

import java.util.List;

public interface SubQueryFactory<T> {
    StringBuilder makeQuery(List<T> ts);
    /*

    public static void completePreparedStatement(
            PreparedStatement findAllStmt,
            List<Filter> filters,
            int startIndexOfThePreparedStatement,
            long page,
            long size
    ) throws SQLException {
        int surPlus = 0;

        for (int i = 0; i < filters.size(); i++) {

            int paramIndex = i + startIndexOfThePreparedStatement + surPlus;
            Filter filter = filters.get(i);


            if(filter.getOperatorType().equals(OperatorType.BETWEEN)) {
                List<Object> values = (List<Object>) filter.getValue();

                findAllStmt.setString(paramIndex , values.getFirst().toString());
                findAllStmt.setString(paramIndex + 1, values.getLast().toString());

                surPlus++;

            } else {
                findAllStmt.setString(i + startIndexOfThePreparedStatement + surPlus, filter.getValue().toString());
            }
        }
        findAllStmt.setLong(filters.size() + startIndexOfThePreparedStatement + surPlus, size);
        findAllStmt.setLong(filters.size() + startIndexOfThePreparedStatement + surPlus + 1, size * (page - 1));
    }
*/
}
