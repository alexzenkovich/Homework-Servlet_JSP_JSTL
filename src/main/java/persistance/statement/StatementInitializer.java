package persistance.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {

    void createQueryStatement(PreparedStatement stmt, T t) throws SQLException;
    void updateQueryStatement(PreparedStatement stmt, T t) throws SQLException;
}
