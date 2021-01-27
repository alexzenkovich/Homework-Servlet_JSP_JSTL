package by.it_academy.repositories;

import by.it_academy.config.ApplicationConfig;
import by.it_academy.model.basket.Basket;
import by.it_academy.model.basket.BasketCell;
import by.it_academy.model.books.Book;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.Role;
import by.it_academy.model.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = ApplicationConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository userRepository;


    private User initUser(){
        User user = new User();
        user.setName("Alex");
        user.setSurname("Zenkovich");
        user.setEmail("sanya@gmail.com");
        user.setAge(36);
        user.setAuthenticate(new Authenticate("login", "pass"));
        user.setRole(Role.USER);
        user.setBasket(new Basket());
        BasketCell basketCell = new BasketCell();
        basketCell.setBook(new Book("a", "t", 123));
        user.getBasket().addBasketCell(basketCell);
        basketCell.setBook(new Book("b", "tit", 345));
        user.getBasket().addBasketCell(basketCell);

        user = em.persist(user);

        return user;
    }

    @Test
    public void findUserByAuthenticateLogin() {
        initUser();
        User user = userRepository.findUserByAuthenticateLogin("login");
        assertNotNull(user);
        assertEquals("login", user.getAuthenticate().getLogin());
    }

    @Test
    public void existsUserByAuthenticateLogin() {
        initUser();
        boolean isUser = userRepository.existsUserByAuthenticateLogin("login");
        assertTrue(isUser);

    }

    @Test
    public void findUserWithBasketById() {
        initUser();
        User user = userRepository.findUserWithBasketById(1L);
        assertNotNull(user);
        assertNotNull(user.getBasket());
    }

    @Test
    public void findAllUsersWithAuthenticate() {
        initUser();
        assertNotNull(userRepository.findAllUsersWithAuthenticate());
    }

    @Test
    public void delete() {
        User user = initUser();
        assertNotNull(user);
        userRepository.delete(user);
        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    public void findUserWithAuthenticateById() {
        User user = initUser();
        User userFromDB = userRepository.findUserWithAuthenticateById(user.getId());
        assertNotNull(userFromDB);
        assertNotNull(userFromDB.getAuthenticate());
    }

    @Test
    public void getSizeUserBasketCellsById() {
        User user = initUser();
        int size = userRepository.getSizeUserBasketCellsById(user.getId());
        assertEquals(2, user.getBasket().getBasketCells().size());
    }

    @Test
    public void findUserWithBasketCellsWithBooksById() {
        User user = initUser();
        User userFromDB = userRepository.findUserWithBasketCellsWithBooksById(user.getId());

        assertNotNull(userFromDB);
        assertNotNull(userFromDB.getBasket().getBasketCells());
        assertEquals(1, userFromDB.getBasket().getBasketCells().size());
    }

    @Test
    public void findAllAdministrators() {
        initUser();
        int numberUsers = userRepository.findAllAdministrators().size();

        assertEquals(2, numberUsers);
    }
}