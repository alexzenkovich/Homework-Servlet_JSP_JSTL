package by.itacademy.persistance.query.impl;

import by.itacademy.persistance.query.JdbcSqlQueryHolder;

public class BookSqlQueryHolder implements JdbcSqlQueryHolder {

    private static final String SELECT_BOOK_BY_ID = "select id, author, title, pages from books where id = ?";
    private static final String SELECT_ALL_BOOKS = "select * from books";
    private static final String UPDATE_USER_BY_ID = "update books set author = ?, title = ?, pages = ? where id = ?";
    private static final String CREATE_BOOK = "insert into books (author, title, pages) values (?,?,?)";
    private static final String DELETE_USER_BY_ID = "delete from books where id = ?";

    @Override
    public String createSql() {
        return CREATE_BOOK;
    }

    @Override
    public String getByIdSql() {
        return SELECT_BOOK_BY_ID;
    }

    @Override
    public String getAllSql() {
        return SELECT_ALL_BOOKS;
    }

    @Override
    public String updateSql() {
        return UPDATE_USER_BY_ID;
    }

    @Override
    public String deleteSql() {
        return DELETE_USER_BY_ID;
    }
}
