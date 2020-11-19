package by.itacademy.services;

import by.itacademy.model.users.User;
import by.itacademy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserLoginExist(String login) {
        return userRepository.existsUserByAuthenticateLoginIsContaining(login);
    }

    public User findUserByAuthenticate_LoginAndAuthenticate_Password(String login, String password) {
        return userRepository.findUserByAuthenticate_LoginAndAuthenticate_Password(login, password);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.
    }
}
