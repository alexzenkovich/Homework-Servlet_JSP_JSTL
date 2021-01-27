package by.it_academy.controllers;

import by.it_academy.config.ApplicationConfig;
import by.it_academy.config.WebConfig;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.User;
import by.it_academy.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Test
    public void loadUsersPage() {
    }

    @Test
    public void loadEditProfilePage() {
    }

    @Test
    public void processEditProfilePage() {
    }

    @Test
    public void loadUserProfilePage() {
    }

    @Test
    public void processBlockingUser() {
    }

    @Test
    public void processUnblockingUser() {
    }

    @Test
    public void processDeletingUser() {
    }
}