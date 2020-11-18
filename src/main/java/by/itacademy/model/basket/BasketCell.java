package by.itacademy.model.basket;

import by.itacademy.model.books.Book;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder

public class BasketCell {
    private long id;
    private Book book;
    private Timestamp dateOfTakingBook;
    private int daysForReading;

    public BasketCell(Book book, Timestamp dateOfTakingBook, int daysForReading) {
        this.book = book;
        this.dateOfTakingBook = dateOfTakingBook;
        this.daysForReading = daysForReading;
    }
}
