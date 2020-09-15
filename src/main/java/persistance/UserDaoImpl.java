package persistance;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserDaoImpl implements UserDao{

    private final static Map<Long, User> USERS = new HashMap<>();
    private final static AtomicLong ID = new AtomicLong(1);

    public User create (User user) {
        long id = ID.getAndIncrement();
        user.setId(id);
        USERS.put(id, user);
        return getById(id);
    }

    public User getById (long id) {
        return USERS.values().stream().filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No exists this user"));
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return USERS.values().stream().filter(user -> user.getAuthenticate().getLogin().equals(login)
                && user.getAuthenticate().getPassword()
                .equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid user authentification data"));
    }

    public boolean isLoginExists(String login) {
        return USERS.values().stream().anyMatch(user -> user.getAuthenticate().getLogin().equals(login));
    }

    public List<User> getUsers() {
        return new ArrayList<>(USERS.values());
    }

    public void update(User user) {
        USERS.put(user.getId(), user);
    }

    public void delete(long id) {
        USERS.remove(id);
    }
}
