package model.basket;

import lombok.*;
import model.books.Book;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
