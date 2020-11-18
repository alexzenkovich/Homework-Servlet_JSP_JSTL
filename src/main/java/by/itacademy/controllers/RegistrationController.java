package by.itacademy.controllers;

import by.itacademy.model.basket.Basket;
import by.itacademy.model.users.Authenticate;
import by.itacademy.model.users.Role;
import by.itacademy.model.users.User;
import by.itacademy.persistance.repositories.repositoryImpl.BookRepositoryImpl;
import by.itacademy.persistance.repositories.repositoryImpl.UserRepositoryImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserRepositoryImpl userDao = new UserRepositoryImpl();
    private final BookRepositoryImpl bookDao = new BookRepositoryImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(name, surname, email, age);
        user.setAuthenticate(new Authenticate(login, password));
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setBasket(new Basket());

        userDao.create(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(600);
        request.setAttribute("books", bookDao.getAll());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
