package persistance.dao.daoImpl;

import connection.DatabaseConnector;
import exception.ApplicationBaseException;
import model.basket.Basket;
import model.users.Authenticate;
import model.users.User;
import persistance.dao.AbstractCrudDao;
import persistance.mapper.ResultSetMapper;
import persistance.mapper.impl.UserResultSetMapper;
import persistance.query.JdbcSqlQueryHolder;
import persistance.query.impl.UserSqlQueryHolder;
import persistance.statement.StatementInitializer;
import persistance.statement.impl.UserStatementInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractCrudDao<User> {

    private final JdbcSqlQueryHolder jdbcSqlQueryHolder;
    private final StatementInitializer statementInitializer;
    private final ResultSetMapper resultSetMapper;

    private final AuthenticateDaoImpl authenticateDao;

    public UserDaoImpl() {
        jdbcSqlQueryHolder = new UserSqlQueryHolder();
        statementInitializer = new UserStatementInitializer();
        resultSetMapper = new UserResultSetMapper();

        authenticateDao = new AuthenticateDaoImpl();
    }

    @Override
    public User create (User user) {
        authenticateDao.create(user.getAuthenticate());
        return super.create(user);
    }

    @Override
    public User getById (long id) {
        return super.getById(id);
    }

    public List<User> getAll() {
        return super.getAll();
    }

    public User update(User user) {
        return super.update(user);
    }

    public void delete(long id) {
        super.delete(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select ID, NAME, SURNAME, EMAIL, AGE, a.LOGIN, a.PASSWORD, " +
                     "a.PROFILE_ENABLE from USERS, AUTHENTICATE a where a.login = ? and a.password = ?")) {
            prStmt.setString(1, login);
            prStmt.setString(2, password);
            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new ApplicationBaseException("Invalid entity login or password: " + login + " " + password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    public Basket getBasketByUserId (long id) {
        return USERS.values().stream().filter(user -> user.getId()==id)
                .findFirst()
                .map(User::getBasket)
                .orElseThrow(() -> new RuntimeException("Whats wrong with basket"));
    }

    public boolean isLoginExists(String login) {
        return USERS.values().stream().anyMatch(user -> user.getAuthenticate().getLogin().equals(login));
    }

    @Override
    protected JdbcSqlQueryHolder getSqlHolder() {
        return jdbcSqlQueryHolder;
    }

    @Override
    protected ResultSetMapper<User> getResultSetMapper() {
        return resultSetMapper;
    }

    @Override
    protected StatementInitializer<User> getStatementInitializer() {
        return statementInitializer;
    }
}
