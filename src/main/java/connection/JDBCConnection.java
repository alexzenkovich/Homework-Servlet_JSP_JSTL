package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection extends DBConnection{

    private static JDBCConnection instance = null;

    private JDBCConnection() {
        init();
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error initialization JDBCConnector: " + e.getMessage());
        }
    }

    public static JDBCConnection getInstance() {
        if (instance == null) {
            instance = new JDBCConnection();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), getUserName(), getPassword());
    }
}
