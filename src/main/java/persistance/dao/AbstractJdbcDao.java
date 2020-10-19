package persistance.dao;

import connection.DatabaseConnector;

public abstract class AbstractJdbcDao {

    private static final DatabaseConnector CONNECTOR = DatabaseConnector.getInstance();

    public DatabaseConnector getConnector() {
        return CONNECTOR;
    }
}
