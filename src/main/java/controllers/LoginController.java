package controllers;

import model.users.Role;
import model.users.User;
import persistance.dao.daoImpl.BookDaoImpl;
import persistance.dao.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();
    private final BookDaoImpl bookDao = new BookDaoImpl();

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
                throw new RuntimeException("User still not exists");
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
        session.setMaxInactiveInterval(100);
        assert user != null;
        if (user.getRole() == Role.ADMINISTRATOR) {
            request.setAttribute("users", userDao.getAll());
        }
        request.setAttribute("books", bookDao.getAll());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
