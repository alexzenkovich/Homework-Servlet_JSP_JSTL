package model.books;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Book {

    private long id;
    private String author;
    private String title;
    private int numberOfPages;
    private boolean isAccess;

    public Book(String author, String title, int numberOfPages, boolean isAccess) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.isAccess = isAccess;
    }
}
