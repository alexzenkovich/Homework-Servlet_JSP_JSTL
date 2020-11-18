package by.itacademy.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Pool extends DBConnection{

    private final int maxStatements = 130;
    private final int maxStatementsPerConnection = 10;
    private final int minPoolSize = 5;
    private final int acquireIncrement = 2;
    private final int maxPoolSize = 25;

    private static C3P0Pool instance = null;
    private DataSource dataSource;

    private C3P0Pool() {
        init();
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(getDriver());
            cpds.setJdbcUrl(getUrl());
            cpds.setUser(getUserName());
            cpds.setPassword(getPassword());

            cpds.setMaxStatements(maxStatements);
            cpds.setMaxStatementsPerConnection(maxStatementsPerConnection);
            cpds.setMinPoolSize(minPoolSize);
            cpds.setAcquireIncrement(acquireIncrement);
            cpds.setMaxPoolSize(maxPoolSize);

            this.dataSource = cpds;
        } catch (PropertyVetoException ex) {
            System.err.println("Error init connectionPool " + ex);
        }
    }

    public static C3P0Pool getInstance() {
        if (instance == null) {
            instance = new C3P0Pool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public int getMaxStatements() {
        return maxStatements;
    }

    public int getMaxStatementsPerConnection() {
        return maxStatementsPerConnection;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public int getAcquireIncrement() {
        return acquireIncrement;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static void setInstance(C3P0Pool instance) {
        C3P0Pool.instance = instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
