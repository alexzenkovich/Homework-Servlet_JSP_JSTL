package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final DatabaseConnector INSTANCE = new DatabaseConnector();

    private static final String user = "sa";
    private static final String pass = "";
    private static final String url = "jdbc:h2:F:\\AndroidJAVA\\ITAcademyJAVAEnterprise" +
            "\\Homework-Servlet_JSP_JSTL\\src\\main\\resources\\tables";

    private DatabaseConnector() {
    }

    public static DatabaseConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            throw new RuntimeException("Error create database connection");
        }
    }
}
