package controllers;

import model.*;
import persistance.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "admin1", value = "Ivam;Ivanov;vanya@gmail.com;25;admin1;1"),
        @WebInitParam(name = "admin2", value = "Peter;Petrov;petya@gmail.com;23;admin2;1")
})
public class LoginController extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void init() throws ServletException {
        String[] firstUser = getInitParameter("admin1").split(";");
        String[] secondUser = getInitParameter("admin2").split(";");

        userDao.create(new User(firstUser[0], firstUser[1], firstUser[2], Integer.parseInt(firstUser[3]),
                new Authenticate(firstUser[4], firstUser[5], false), Role.ADMINISTRATOR));
        userDao.create(new User(secondUser[0], secondUser[1], secondUser[2], Integer.parseInt(secondUser[3]),
                new Authenticate(secondUser[4], secondUser[5], false), Role.ADMINISTRATOR));
    }

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
            request.getSession().setAttribute("error", e.getMessage());
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
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
