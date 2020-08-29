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

@WebServlet(value = "/login", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "admin1", value = "admin1;1"),
        @WebInitParam(name = "admin2", value = "admin2;1")
})
public class LoginController extends HttpServlet {

    private final UserDao USER_DAO = new UserDaoImpl();

    public void init() throws ServletException {
        String[] firstUser = getInitParameter("admin1").split(";");
        String[] secondUser = getInitParameter("admin2").split(";");

        USER_DAO.save(new User(firstUser[0], firstUser[1], Role.ADMIN));
        USER_DAO.save(new User(secondUser[0], secondUser[1], Role.ADMIN));
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
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
