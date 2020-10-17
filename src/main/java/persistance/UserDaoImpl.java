package persistance;

import connection.DatabaseConnector;
import model.basket.Basket;
import model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserDaoImpl extends UserDao {

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    private final static Map<Long, User> USERS = new HashMap<>();
    private final static AtomicLong ID = new AtomicLong(1);

    @Override
    public User create (User user) {

        User u = new User();
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO USERS (name, surname, email, age, AUTHENTICATE_ID, BASKET_ID) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public User getById (long id) {
        User user = new User();
        try (Connection connection = databaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where ID = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setSurname(resultSet.getString("SURNAME"));
                user.setAge(resultSet.getInt("AGE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return USERS.values().stream().filter(user -> user.getAuthenticate().getLogin().equals(login)
                && user.getAuthenticate().getPassword()
                .equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid user authentification data"));
    }

    public Basket getBasketByUserId (long id) {
        return USERS.values().stream().filter(user -> user.getId()==id)
                .findFirst()
                .map(User::getBasket)
                .orElseThrow(() -> new RuntimeException("Whats wrong with basket"));
    }

    public boolean isLoginExists(String login) {
        return USERS.values().stream().anyMatch(user -> user.getAuthenticate().getLogin().equals(login));
    }

    public List<User> getAll() {
        return new ArrayList<>(USERS.values());
    }

    public void update(User user) {
        USERS.put(user.getId(), user);
    }

    public void delete(long id) {
        USERS.remove(id);
    }
}
