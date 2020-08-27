package controllers;

import model.Role;
import model.User;
import persistance.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(value = "/login", initParams = {
        @WebInitParam(name = "admin1", value = "admin;1"),
        @WebInitParam(name = "admin2", value = "admin2;2")
})
public class LoginController extends HttpServlet {

    private final static UserDao USER_DAO = new UserDaoImpl();

    @Override
    public void init() {
        ServletContext context = getServletContext();
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context.getInitParameter(name);
            String[] values = value.split(";");
            String login = values[0];
            String password = values[1];
            USER_DAO.save(new User(login, password, Role.ADMIN));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = USER_DAO.getUserByLoginAndPassword(login, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);
            if (user.getRole() == Role.ADMIN) {
                request.setAttribute("users", USER_DAO.getUsers());
            }

            getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
        }

        request.setAttribute("error", "Invalid user data");
        request.setAttribute("params", USER_DAO.getUsers());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
