package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static constants.Constants.*;

public class DatabaseConnector {

    private static final DatabaseConnector INSTANCE = new DatabaseConnector();

    private static final String DATABASE_DRIVER;
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    static {
        try (InputStream resourceAsStream = DatabaseConnector.class.getResourceAsStream(DATABASE_PROPERTIES_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            DATABASE_DRIVER = properties.getProperty(DATABASE_DRIVER_KEY);
            DATABASE_URL = properties.getProperty(DATABASE_URL_KEY);
            DATABASE_USER = properties.getProperty(DATABASE_USER_KEY);
            DATABASE_PASSWORD = properties.getProperty(DATABASE_PASSWORD_KEY);

            Class.forName(DATABASE_DRIVER);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.err.println("Error initialization DataBaseConnector: " + e.getMessage());
            throw new RuntimeException("Error initialization DataBaseConnector: " + e.getMessage());
        }
    }

    private DatabaseConnector() {
    }

    public static DatabaseConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
