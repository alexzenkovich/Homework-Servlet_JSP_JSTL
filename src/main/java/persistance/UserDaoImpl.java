package persistance;

import model.Role;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private static final List<User> USERS = new ArrayList<>();

    public User getUserByLoginAndPassword(String login, String password) {
        for (User user : USERS) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean isLoginExists (User user) {
        boolean isloginExists = false;
        for (User u : USERS) {
            if (u.getLogin().equals(user.getLogin())) {
                isloginExists = true;
            }
        }
        return isloginExists;
    }

    public User save(User user) {
        if (!isLoginExists(user)) {
            USERS.add(user);
        } else {
            return null;
        }
        return user;
    }

    public List<User> getUsers() {
        return new ArrayList<>(USERS);
    }
}
