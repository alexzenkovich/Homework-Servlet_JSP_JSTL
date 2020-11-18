package by.itacademy.persistance.repositories.repositoryImpl;


import by.itacademy.exception.ApplicationBaseException;
import by.itacademy.model.basket.BasketCell;
import by.itacademy.persistance.repositories.AbstractJdbcRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BasketCellRepositoryImpl extends AbstractJdbcRepository {

    private final BookRepositoryImpl bookDao;

    public BasketCellRepositoryImpl() {
        bookDao = new BookRepositoryImpl();
    }

    public void create(BasketCell basketCell, long basketId) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("insert into BASKETCELLS (BOOK_ID, DAYS_FOR_READING, " +
                     "DATE_OF_TAKING_BOOK, BASKET_ID) VALUES ( ?,?,?,? )")) {
            prStmt.setLong(1, basketCell.getBook().getId());
            prStmt.setInt(2, basketCell.getDaysForReading());
            prStmt.setTimestamp(3, basketCell.getDateOfTakingBook());
            prStmt.setLong(4, basketId);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
    }

    public BasketCell getBasketCellByBasketIdAndBookId (long basketId, long bookId) {

        BasketCell basketCell = null;

        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select * from BASKETCELLS " +
                     "where BASKET_ID = ? and BOOK_ID = ?")) {
            prStmt.setLong(1, basketId);
            prStmt.setLong(2, bookId);

            try (ResultSet rs = prStmt.executeQuery()){
                basketCell = new BasketCell();
                basketCell.setId(rs.getLong("id"));
                basketCell.setBook(bookDao.getById(rs.getInt("book_id")));
                basketCell.setDaysForReading(rs.getInt("days_for_reading"));
                basketCell.setDateOfTakingBook(rs.getTimestamp("date_of_taking_book"));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
        return basketCell;
    }

    public List<BasketCell> getBasketCellsByBasketId (long basketId) {

        List<BasketCell> basketCells = new ArrayList<>();
        BasketCell basketCell = null;

        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select * from BASKETCELLS where BASKET_ID = ?")) {
            prStmt.setLong(1, basketId);

            try (ResultSet rs = prStmt.executeQuery()){
                while (rs.next()) {
                    basketCell = new BasketCell();
                    basketCell.setId(rs.getLong("id"));
                    basketCell.setBook(bookDao.getById(rs.getInt("book_id")));
                    basketCell.setDaysForReading(rs.getInt("days_for_reading"));
                    basketCell.setDateOfTakingBook(rs.getTimestamp("date_of_taking_book"));
                    basketCells.add(basketCell);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
        return basketCells;
    }

    public void delete (long bookId) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("DELETE FROM BASKETCELLS WHERE BOOK_ID=?")) {
            prStmt.setLong(1, bookId);

            prStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
