package by.itacademy.persistance.repositories;

import by.itacademy.connection.ConnectionFactory;
import by.itacademy.connection.DBConnection;

public abstract class AbstractJdbcRepository {

    private static final DBConnection CONNECTOR = ConnectionFactory.getConnectionPool("JDBC");

    public DBConnection getConnector() {
        return CONNECTOR;
    }
}
