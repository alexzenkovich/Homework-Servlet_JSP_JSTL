package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.users.Role;
import by.it_academy.model.users.User;
import by.it_academy.services.BasketCellService;
import by.it_academy.services.BasketService;
import by.it_academy.services.BookService;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import static by.it_academy.constants.ErrorConstants.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ModelAndView processEditProfilePage(@SessionAttribute("user") User user) {

        try {
            if (user.getName() == null || user.getName().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_NAME);
            }
            if (user.getSurname() == null || user.getSurname().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_SURNAME);
            }
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_EMAIL);
            }
            if (user.getAge() == 0 || String.valueOf(user.getAge()).isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_AGE);
            }
            if (user.getAuthenticate().getLogin() == null || user.getAuthenticate().getLogin().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_LOGIN);
            }
            if (user.getAuthenticate().getPassword() == null || user.getAuthenticate().getPassword().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_PASSWORD);
            }
            User changedUser = userService.update(user);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", changedUser);
            modelAndView.setViewName("user");

            if (user.getRole() == Role.ADMINISTRATOR) {
                modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            }

            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("templates/edit_profile");

            return modelAndView;
        }
    }

    @PostMapping("/profile")
    public ModelAndView loadUserProfilePage(@SessionAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
        if (user.getRole() == Role.ADMINISTRATOR) {
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
        }
        return modelAndView;
    }

    @PostMapping("/blocking")
    public ModelAndView processBlockingUser(@SessionAttribute("user") User user,
            @RequestParam Long userId) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            userService.disableUserProfile(userId);

            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("user");
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView("user");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        }
    }

    @PostMapping("/unblocking")
    public ModelAndView processUnblockingUser(@SessionAttribute("user") User user,
                                            @RequestParam Long userId) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            userService.enableUserProfile(userId);
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            modelAndView.setViewName("user");
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView("user");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        }
    }

    @PostMapping("/deleteUser")
    public ModelAndView processDeletingUser(@SessionAttribute("user") User user, @RequestParam Long userId) {
        try{
            userService.deleteUserById(userId);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView("user");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        }
    }


}
