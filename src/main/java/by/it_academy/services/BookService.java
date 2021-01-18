package by.it_academy.services;

import by.it_academy.model.books.Book;
import by.it_academy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        String author;
        String title;
        int nop;
        for (int i = 0; i < 100; i++) {
            author = String.valueOf((char) (Math.random() * 100));
            title = String.valueOf((char) (Math.random() * 100));
            nop = (int) (Math.random() * 1000);
            bookRepository.save(new Book(author, title, nop));
        }
//        bookRepository.save(new Book("Arthur Haily", "Airport", 5));
//        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
//        bookRepository.save(new Book("Korney Chukovskiy", "Aibolit", 4));
//        bookRepository.save(new Book("Korney Chukovskiy", "Moidodyr", 8));
//        bookRepository.save(new Book("Korney Chukovskiy", "Tarakanishche", 3));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
