package persistance.dao.daoImpl;

import exception.ApplicationBaseException;
import model.books.Book;
import persistance.dao.AbstractCrudDao;
import persistance.mapper.ResultSetMapper;
import persistance.mapper.impl.BookResultSetMapper;
import persistance.query.JdbcSqlQueryHolder;
import persistance.query.impl.BookSqlQueryHolder;
import persistance.statement.StatementInitializer;
import persistance.statement.impl.BookStatementInitializer;
import java.sql.*;
import java.util.List;

public class BookDaoImpl extends AbstractCrudDao<Book> {

    private final JdbcSqlQueryHolder bookJdbcSqlQueryHolder;
    private final StatementInitializer<Book> bookStatementInitializer;
    private final ResultSetMapper<Book> bookResultSetMapper;

    public BookDaoImpl () {
        bookJdbcSqlQueryHolder = new BookSqlQueryHolder();
        bookStatementInitializer = new BookStatementInitializer();
        bookResultSetMapper = new BookResultSetMapper();
    }

    @Override
    public void create(Book book) {
        super.create(book);
    }

    @Override
    public Book getById(long id) {
        return super.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return super.getAll();
    }

    @Override
    public Book update(Book book) {
        return super.update(book);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    public Book getBookByAuthor(String author) {
        try(Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement("select ID, AUTHOR, TITLE, PAGES from BOOKS where AUTHOR = ?")) {
            prStmt.setString(1, "author");
            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new ApplicationBaseException("Invalid entity author: " + author);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getBookByAuthor entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    public Book getBookByTitle(String title) {
        try(Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement("select ID, AUTHOR, TITLE, PAGES from BOOKS where TITLE = ?")) {
            prStmt.setString(1, "title");
            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new ApplicationBaseException("Invalid entity title: " + title);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getBookByAuthor entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    protected JdbcSqlQueryHolder getSqlHolder() {
        return bookJdbcSqlQueryHolder;
    }

    @Override
    protected ResultSetMapper<Book> getResultSetMapper() {
        return bookResultSetMapper;
    }

    @Override
    protected StatementInitializer<Book> getStatementInitializer() {
        return bookStatementInitializer;
    }
}
