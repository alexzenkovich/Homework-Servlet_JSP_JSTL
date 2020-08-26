package controllers;

import model.Role;
import model.User;
import persistance.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login", initParams = {
        @WebInitParam(name = "admin1", value = "admin1;1"),
        @WebInitParam(name = "admin2", value = "admin2;1")
})
public class LoginController extends HttpServlet {

    private final static UserDao USER_DAO = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String[] params1 = getServletContext().getInitParameter("admin1").split(";");
//        String[] params2 = getServletContext().getInitParameter("admin2").split(";");
//        USER_DAO.save(new User(params1[0], params1[1], Role.ADMIN));
//        USER_DAO.save(new User(params2[0], params2[1], Role.ADMIN));
//
//        if (params1.length > 0) {
//            request.setAttribute("params", params1);
//            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//        } else {
//            request.setAttribute("params", "no param");
//            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//        }

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
