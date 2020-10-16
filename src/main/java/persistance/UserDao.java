package persistance;

import model.users.User;

import java.util.List;

public interface UserDao {

    User create (User user);

    User getById (long id);

    User getUserByLoginAndPassword(String login, String password);

    boolean isLoginExists (String login);

    List<User> getUsers();

    void update (User user);

    public void delete(long id);
}
