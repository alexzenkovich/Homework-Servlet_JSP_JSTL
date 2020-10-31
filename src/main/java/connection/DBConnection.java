package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static constants.Constants.*;

public abstract class DBConnection {

    private String driver;
    private String url;
    private String userName;
    private String password;

    public abstract Connection getConnection() throws SQLException;

    public void init() {
        try (InputStream resourceAsStream = DBConnection.class.getResourceAsStream(DATABASE_PROPERTIES_FILE_PATH)){
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            driver = properties.getProperty(DATABASE_DRIVER_KEY);
            url = properties.getProperty(DATABASE_FILE_URL_KEY);
            userName = properties.getProperty(DATABASE_USER_KEY);
            password = properties.getProperty(DATABASE_PASSWORD_KEY);

            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.err.println("Error initialization DataBaseConnector: " + e.getMessage());
            throw new RuntimeException("Error initialization DataBaseConnector: " + e.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
