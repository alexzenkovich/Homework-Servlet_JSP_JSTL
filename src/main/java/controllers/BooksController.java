package controllers;

import model.books.Book;
import model.users.Authenticate;
import model.users.Role;
import model.users.User;
import persistance.BookDaoImpl;
import persistance.CrudDao;
import persistance.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"","/books"}, loadOnStartup = 1, initParams = {
        @WebInitParam(name = "book1", value = "Arthur Haily;Airport;315;true"),
        @WebInitParam(name = "book2", value = "Korney Chukovskiy;Aibolit;5;false"),
        @WebInitParam(name = "book3", value = "Korney Chukovskiy;Aibolit;5;true"),
        @WebInitParam(name = "book4", value = "Korney Chukovskiy;Moidodyr;8;false"),
        @WebInitParam(name = "book5", value = "Korney Chukovskiy;Tarakanishche;3;true"),
        @WebInitParam(name = "admin1", value = "Ivan;Ivanov;vanya@gmail.com;25;admin1;1"),
        @WebInitParam(name = "admin2", value = "Peter;Petrov;petya@gmail.com;23;admin2;1"),
        @WebInitParam(name = "user1", value = "user;user;user@gmail.com;18;user;user")
})
public class BooksController extends HttpServlet {

    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final UserDaoImpl crudDao = new UserDaoImpl();

//    @Override
//    public void init() throws ServletException {
//        String[] firstBook = getInitParameter("book1").split(";");
//        String[] secondBook = getInitParameter("book2").split(";");
//        String[] thirdBook = getInitParameter("book3").split(";");
//        String[] fourthBook = getInitParameter("book4").split(";");
//        String[] fifthBook = getInitParameter("book5").split(";");
//        String[] firstUser = getInitParameter("admin1").split(";");
//        String[] secondUser = getInitParameter("admin2").split(";");
//        String[] thirdUser = getInitParameter("user1").split(";");
//
//        bookDao.create(new Book(firstBook[0], firstBook[1], Integer.parseInt(firstBook[2]),
//                Boolean.parseBoolean(firstBook[3])));
//        bookDao.create(new Book(secondBook[0], secondBook[1], Integer.parseInt(secondBook[2]),
//                Boolean.parseBoolean(secondBook[3])));
//        bookDao.create(new Book(thirdBook[0], thirdBook[1], Integer.parseInt(thirdBook[2]),
//                Boolean.parseBoolean(thirdBook[3])));
//        bookDao.create(new Book(fourthBook[0], fourthBook[1], Integer.parseInt(fourthBook[2]),
//                Boolean.parseBoolean(fourthBook[3])));
//        bookDao.create(new Book(fifthBook[0], fifthBook[1], Integer.parseInt(fifthBook[2]),
//                Boolean.parseBoolean(fifthBook[3])));
//        crudDao.create(new User(firstUser[0], firstUser[1], firstUser[2], Integer.parseInt(firstUser[3]),
//                new Authenticate(firstUser[4], firstUser[5], false), Role.ADMINISTRATOR));
//        crudDao.create(new User(secondUser[0], secondUser[1], secondUser[2], Integer.parseInt(secondUser[3]),
//                new Authenticate(secondUser[4], secondUser[5], false), Role.ADMINISTRATOR));
//        crudDao.create(new User(thirdUser[0], thirdUser[1], thirdUser[2], Integer.parseInt(thirdUser[3]),
//                new Authenticate(thirdUser[4], thirdUser[5], false), Role.USER));
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", bookDao.getAll());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
