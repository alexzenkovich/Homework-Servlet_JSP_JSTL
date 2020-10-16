package persistance;

import model.books.Book;
import model.users.User;

import java.util.List;

public interface BookDao {

    Book create (Book book);

    Book getById (long id);

    Book getBookByAuthor (String author);

    Book getBookByTitle (String title);

    List<Book> getBooks();

    void update (Book book);

    public void delete(long id);
}

