package controllers;

import model.users.Role;
import model.users.User;
import persistance.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;

        try {
            if (login == null || login.isEmpty()) {
                throw new RuntimeException("Invalid user login");
            }
            if (password == null || password.isEmpty()) {
                throw new RuntimeException("Invalid user password");
            }
            if (!userDao.isLoginExists(login)) {
                throw new RuntimeException("User already exists");
            } else {
                user = userDao.getUserByLoginAndPassword(login, password);
            }

        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            response.sendRedirect("/index.jsp");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(600);
        assert user != null;
        if (user.getRole() == Role.ADMINISTRATOR) {
            request.setAttribute("users", userDao.getUsers());
        }
        request.setAttribute("books", bookDao.getBooks());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
