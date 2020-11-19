package by.itacademy.repositories;

import by.itacademy.model.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsUserByAuthenticateLoginIsContaining(String login);
    User findUserByAuthenticate_LoginAndAuthenticate_Password(String login, String password);
    List<User> findAll();

}
