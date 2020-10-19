package persistance.mapper.impl;

import model.books.Book;
import persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookResultSetMapper implements ResultSetMapper<Book> {
    @Override
    public Book processResultSet(ResultSet rs) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .author(rs.getString("author"))
                .title(rs.getString("title"))
                .numberOfPages(rs.getInt("pages"))
                .build();
    }
}
