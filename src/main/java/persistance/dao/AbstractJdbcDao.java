package persistance.dao;

import connection.ConnectionFactory;
import connection.DBConnection;

public abstract class AbstractJdbcDao {

    private static final DBConnection CONNECTOR = ConnectionFactory.getConnectionPool("JDBC");

    public DBConnection getConnector() {
        return CONNECTOR;
    }
}
