package by.itacademy.model.basket;

import by.itacademy.model.books.Book;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"book", "basket"})
@ToString(exclude = {"book", "basket"})

@Entity
@Table
public class BasketCell {

    @Id
    @GeneratedValue
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp dateOfTakingBook;
    private int daysForReading;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Basket basket;

    public BasketCell(Timestamp dateOfTakingBook, int daysForReading) {

        this.dateOfTakingBook = dateOfTakingBook;
        this.daysForReading = daysForReading;
    }

    public void addBook(Book book) {
        this.setBook(book);
        book.getBasketCells().add(this);
    }

    public void removeBook(Book book) {
        this.setBook(null);
        book.getBasketCells().remove(this);
    }
}
