package by.itacademy.persistance.dao;

import by.itacademy.connection.ConnectionFactory;
import by.itacademy.connection.DBConnection;

public abstract class AbstractJdbcDao {

    private static final DBConnection CONNECTOR = ConnectionFactory.getConnectionPool("JDBC");

    public DBConnection getConnector() {
        return CONNECTOR;
    }
}
