package persistance;

import model.users.User;

import java.util.List;

abstract class UserDao implements CrudDao<User>{


    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }
}
