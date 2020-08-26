package persistance;

import model.User;

import java.util.List;

public interface UserDao {

    User getUserByLoginAndPassword(String login, String password);

    User save(User user);

    List<User> getUsers();
}
