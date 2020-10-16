package controllers;

import model.basket.Basket;
import model.basket.BasketCell;
import model.books.Book;
import model.users.User;
import persistance.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "BasketController", urlPatterns = "/basket")
public class BasketController extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();
    private final BasketDaoImpl basketDao = new BasketDaoImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        long bookId = Long.parseLong(req.getParameter("bookId"));
        long userId = Long.parseLong(req.getParameter("userId"));

        User user = userDao.getById(userId);
        Book book = bookDao.getById(bookId);
        Basket basket = userDao.getBasketByUserId(userId);

        if ("addBook".equals(action)) {
            if (book != null && basket != null) {
                int daysForReading = Integer.parseInt(req.getParameter("daysForReading"));
                basketDao.save(new BasketCell(book, LocalDate.now(), daysForReading));
                req.setAttribute("basketMessage", "Book was added to your basket");
            }
            req.setAttribute("numberOfBooksInBasket", userDao.getById(user.getId()).getBasket().getBookcells().size());
            req.setAttribute("books", bookDao.getBooks());
        }
        if ("deleteBook".equals(action)) {
            if (book != null) {
                basketDao.delete(book.getId());
                req.setAttribute("basketMessage", String.format("Book %s by author %s was deleted from your basket",
                        book.getTitle(), book.getAuthor()));
            }
        }
        if ("readBook".equals(action)) {

            if (book != null) {
                req.setAttribute("bookInfo", book);
            }
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        try {
            req.setAttribute("booksFromBasket", userDao.getById(user.getId()).getBasket().getBookcells());
        } catch (RuntimeException e) {
            req.setAttribute("emptyBasket", e.getMessage());
            resp.sendRedirect("/index.jsp");
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
