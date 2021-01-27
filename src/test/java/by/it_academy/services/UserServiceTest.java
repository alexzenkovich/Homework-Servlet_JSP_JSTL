package by.it_academy.services;

import by.it_academy.config.ApplicationConfig;
import by.it_academy.model.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User user = new User();
        user.setName("Alex");
        user.setSurname("Zenkovich");
        user.setEmail("sanya@gmail.com");

        User userFromDB = userService.create(user);

        assertNotNull(userFromDB);
        assertNotNull(userFromDB.getId());
        assertEquals("Alex", userFromDB.getName());
    }
}