package persistance.statement.impl;

import model.books.Book;
import persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookStatementInitializer implements StatementInitializer<Book> {
    @Override
    public void createQueryStatement(PreparedStatement prStmt, Book book) throws SQLException {
        prStmt.setString(1, book.getAuthor());
        prStmt.setString(2, book.getTitle());
        prStmt.setInt(3, book.getNumberOfPages());
    }

    @Override
    public void updateQueryStatement(PreparedStatement prStmt, Book book) throws SQLException {
        prStmt.setString(1, book.getAuthor());
        prStmt.setString(2, book.getTitle());
        prStmt.setInt(3, book.getNumberOfPages());
    }
}
