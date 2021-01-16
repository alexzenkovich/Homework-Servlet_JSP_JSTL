package by.it_academy.controllers;

import by.it_academy.exception.ApplicationBaseException;
import by.it_academy.model.users.Authenticate;
import by.it_academy.model.users.Role;
import by.it_academy.model.users.User;
import by.it_academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import static by.it_academy.constants.ErrorConstants.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView loadUsersPage(@AuthenticationPrincipal User user) {
        try {
            ModelAndView modelAndView = new ModelAndView("users");
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            if (user.getRole() == Role.ADMINISTRATOR) {
                modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            }
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/update")
    public ModelAndView loadEditProfilePage() {
        ModelAndView modelAndView = new ModelAndView("templates/edit_profile");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("authenticate", new Authenticate());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView processEditProfilePage(@AuthenticationPrincipal User userPrincipal,
                                               @Validated(value = User.class)
                                               @ModelAttribute("user") User user,
                                               @ModelAttribute("authenticate") Authenticate authenticate) {

        try {
            ModelAndView modelAndView = new ModelAndView();

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
            if (authenticate.getLogin() == null || authenticate.getLogin().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_LOGIN);
            }
            if (authenticate.getPassword() == null || authenticate.getPassword().isEmpty()) {
                throw new ApplicationBaseException(INVALID_USER_PASSWORD);
            }
            userPrincipal.setName(user.getName());
            userPrincipal.setSurname(user.getSurname());
            userPrincipal.setEmail(user.getEmail());
            userPrincipal.setAge(user.getAge());
            userPrincipal.getAuthenticate().setLogin(authenticate.getLogin());
            userPrincipal.getAuthenticate().setPassword(authenticate.getPassword());

            User changedUser = userService.update(userPrincipal);

            if (changedUser == null) {
                throw new ApplicationBaseException(INVALID_UPDATE_USER);
            }

            modelAndView.addObject("user", changedUser);
            modelAndView.setViewName("profile");

            if (changedUser.getRole() == Role.ADMINISTRATOR) {
                modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            }

            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/profile")
    public ModelAndView loadUserProfilePage(@AuthenticationPrincipal User user) {
        try {
            ModelAndView modelAndView = new ModelAndView("profile");
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/blocking")
    public ModelAndView processBlockingUser(@AuthenticationPrincipal User user,
                                            @RequestParam Long userId) {
        try {
            userService.disableUserProfile(userId);
            ModelAndView modelAndView = new ModelAndView("users");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (ApplicationBaseException e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/unblocking")
    public ModelAndView processUnblockingUser(@AuthenticationPrincipal User user,
                                              @RequestParam Long userId) {
        try {
            userService.enableUserProfile(userId);
            ModelAndView modelAndView = new ModelAndView("users");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (ApplicationBaseException e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/deleteUser")
    public ModelAndView processDeletingUser(@AuthenticationPrincipal User user, @RequestParam Long userId) {
        try{
            userService.deleteUserById(userId);
            ModelAndView modelAndView = new ModelAndView("users");
            modelAndView.addObject("users", userService.findAllUsersWithAuthenticate());
            modelAndView.addObject("numberOfBooksInBasket", userService.countUserBasketBasketCellsById(user.getId()));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
