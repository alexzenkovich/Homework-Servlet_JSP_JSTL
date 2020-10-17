package persistance;

import model.books.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BookDaoImpl extends BookDao {

    private final static Map<Long, Book> BOOKS = new HashMap<>();
    private final static AtomicLong BOOKID = new AtomicLong(1);

    @Override
    public Book create(Book book) {
        long id = BOOKID.getAndIncrement();
        book.setId(id);
        BOOKS.put(id, book);
        return book;
    }

    @Override
    public Book getById(long id) {
        return BOOKS.values().stream().filter(book -> book.getId()==id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No exists this book"));
    }

    public Book getBookByAuthor(String author) {
        return BOOKS.values().stream().filter(book -> book.getAuthor().equals(author))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no books written by this author"));
    }

    public Book getBookByTitle(String title) {
        return BOOKS.values().stream().filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no books with this title"));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(BOOKS.values());
    }

    @Override
    public void update(Book book) {
        BOOKS.put(book.getId(), book);
    }

    @Override
    public void delete(long id) {
        BOOKS.remove(id);
    }
}
