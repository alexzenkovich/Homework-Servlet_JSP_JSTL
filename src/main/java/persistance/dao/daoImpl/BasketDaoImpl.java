package persistance.dao.daoImpl;

import exception.ApplicationBaseException;
import model.basket.Basket;
import model.basket.BasketCell;
import persistance.dao.AbstractJdbcDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDaoImpl extends AbstractJdbcDao {

    private final BasketCellDaoImpl basketCellDao;

    public BasketDaoImpl() {
        basketCellDao = new BasketCellDaoImpl();
    }

    public void create(long userId) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("insert into BASKETS (USER_ID) VALUES ( ? )")) {
            prStmt.setLong(1, userId);

            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    public Basket getBasketByUserId (long userId) {

        Basket basket = null;

        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select * from BASKETS where USER_ID = ?")) {
            prStmt.setLong(1, userId);

            try (ResultSet rs = prStmt.executeQuery()) {
                if (rs.next()) {
                    basket = new Basket();
                    basket.setId(rs.getLong("id"));
                    basket.setBasketcells(basketCellDao.getBasketCellsByBasketId(basket.getId()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
        return basket;
    }

    public void delete(long id) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("DELETE FROM BASKETS WHERE id=?")) {
            prStmt.setLong(1, id);

            prStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void deleteBasketCell(long bookId) {
        basketCellDao.delete(bookId);
    }

    public void addBasketCell(BasketCell basketCell, long basketId) {
        basketCellDao.create(basketCell, basketId);
    }
}
