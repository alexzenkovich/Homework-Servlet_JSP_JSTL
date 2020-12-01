package by.it_academy.services;

import by.it_academy.model.books.Book;
import by.it_academy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        bookRepository.save(new Book("Arthur Haily", "Airport", 5));
        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
        bookRepository.save(new Book("Korney Chukovskiy", "Moidodyr", 8));
        bookRepository.save(new Book("Korney Chukovskiy", "Tarakanishche", 3));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookWithBasketCellsById(Long id) { return bookRepository.findBookWithBasketCellsById(id); }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }
}
