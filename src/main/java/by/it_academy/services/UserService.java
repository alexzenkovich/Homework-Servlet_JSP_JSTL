package by.it_academy.services;

import by.it_academy.model.basket.Basket;
import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.books.Book;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.Role;
import by.it_academy.model.users.User;
import by.it_academy.repositories.BasketCellRepository;
import by.it_academy.repositories.BookRepository;
import by.it_academy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private BookRepository bookRepository;

    @Autowired
    private BasketCellService basketCellService;

    @PostConstruct()
    public void usersInit() {

        User admin1 = new User("ivan", "ivanov", "vanya@gmail.com", 25);
        User admin2 = new User("petya", "petrov", "petya@gmail.com", 28);
        User user = new User("Bill", "smit", "bill@gmail.com", 18);
        admin1.setAuthenticate(new Authenticate("admin1", "1"));
        admin2.setAuthenticate(new Authenticate("admin2", "1"));
        user.setAuthenticate(new Authenticate("user", "user"));
        admin1.setRole(Role.ADMINISTRATOR);
        admin2.setRole(Role.ADMINISTRATOR);
        user.setRole(Role.USER);
        admin1.setBasket(new Basket());
        admin2.setBasket(new Basket());
        user.setBasket(new Basket());
        userRepository.save(admin1);
        userRepository.save(admin2);
        userRepository.save(user);
    }

    public boolean existsUserByAuthenticateLogin(String login) {
        return userRepository.existsUserByAuthenticateLogin(login);
    }

    public User findUserWithAuthenticateByLoginAndPassword(String login, String password) {
        return userRepository.findUserWithAuthenticateByLoginAndPassword(login, password);
    }

    public User findUserWithAuthenticateAndBasketById(long id) {
        return userRepository.findUserWithAuthenticateAndBasketById(id);
    }

    public User findUserWithBasketById(Long id) {
        return userRepository.findUserWithBasketById(id);
    }

    public User create(User user) {
        user.setBasket(new Basket());
        user.setRole(Role.USER);
        User createdUser = userRepository.save(user);
        return findUserWithBasketById(createdUser.getId());
    }

    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.removeUserById(userId);
    }

    public List<User> findAllUsersWithAuthenticate() {
        return userRepository.findAllUsersWithAuthenticate();
    }

    public void addBookToUser(long userId, long bookId, int daysForReading){
        BasketCell basketCell = new BasketCell(Timestamp.valueOf(LocalDateTime.now()), daysForReading);
        Book book = bookRepository.findBookWithBasketCellsById(bookId);
        book.addBasketCell(basketCell);
        User user = userRepository.findUserWithBasketById(userId);
        user.getBasket().addBasketCell(basketCell);
        userRepository.saveAndFlush(user);
    }

    public void disableUserProfile(long userId) {
        User u = userRepository.findUserWithAuthenticateById(userId);
        u.getAuthenticate().setProfileEnable(false);
        userRepository.saveAndFlush(u);
    }

    public void enableUserProfile(long userId) {
        User u = userRepository.findUserWithAuthenticateById(userId);
        u.getAuthenticate().setProfileEnable(true);
        userRepository.saveAndFlush(u);
    }

    public int countUserBasketBasketCellsById(long id){
        return userRepository.getSizeUserBasketCellsById(id);
    }

    public void deleteBookByUserId(long userId, long basketCellId) {
        User user = findUserWithBasketById(userId);
        BasketCell basketCell = basketCellService.findBasketCellById(basketCellId);
        user.getBasket().removeBasketCell(basketCell);
        userRepository.saveAndFlush(user);
    }
}
