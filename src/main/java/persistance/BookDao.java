package persistance;

import model.books.Book;

import java.util.List;

public class BookDao implements CrudDao<Book> {
    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(long id) {

    }
}
