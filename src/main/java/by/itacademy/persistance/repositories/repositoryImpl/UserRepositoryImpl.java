package by.itacademy.persistance.repositories.repositoryImpl;

import by.itacademy.exception.ApplicationBaseException;
import by.itacademy.model.users.User;
import by.itacademy.persistance.repositories.AbstractCrudRepository;
import by.itacademy.persistance.mapper.ResultSetMapper;
import by.itacademy.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.persistance.query.JdbcSqlQueryHolder;
import by.itacademy.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.persistance.statement.StatementInitializer;
import by.itacademy.persistance.statement.impl.UserStatementInitializer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractCrudRepository<User> {

    private final JdbcSqlQueryHolder jdbcSqlQueryHolder;
    private final StatementInitializer<User> statementInitializer;
    private final ResultSetMapper<User> resultSetMapper;

    private final AuthenticateRepositoryImpl authenticateRepository;
    private final BasketRepositoryImpl basketRepository;
    private final RoleRepositoryImpl roleRepository;

    public UserRepositoryImpl() {
        jdbcSqlQueryHolder = new UserSqlQueryHolder();
        statementInitializer = new UserStatementInitializer();
        resultSetMapper = new UserResultSetMapper();

        authenticateRepository = new AuthenticateRepositoryImpl();
        basketRepository = new BasketRepositoryImpl();
        roleRepository = new RoleRepositoryImpl();
    }

    @Override
    public void create (User user) {
        super.create(user);
        authenticateRepository.create(user.getAuthenticate());
        roleRepository.create(user.getId(), user.getRole());
        basketRepository.create(user.getId());

    }

    @Override
    public User getById (long id) {
        User user = super.getById(id);
        user.setAuthenticate(authenticateRepository.getById(user.getId()));
        user.setRole(roleRepository.getRoleByUserId(user.getId()));
        user.setBasket(basketRepository.getBasketByUserId(user.getId()));
        return user;
    }

    public List<User> getAll() {
        List<User> users = super.getAll();
        for (User u : users) {
            u.setAuthenticate(authenticateRepository.getById(u.getId()));
            u.setRole(roleRepository.getRoleByUserId(u.getId()));
            u.setBasket(basketRepository.getBasketByUserId(u.getId()));
        }
        return users;
    }

    public User update(User user) {
        return super.update(user);
    }

    public void delete(long id) {
        super.delete(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select u.ID, NAME, SURNAME, EMAIL, AGE\n" +
                     "from USERS u, AUTHENTICATE a where u.ID = a.USER_ID and a.login = ? and a.password = ?")) {
            prStmt.setString(1, login);
            prStmt.setString(2, password);
            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    user = getById(rs.getLong("id"));
                    user.setAuthenticate(authenticateRepository.getById(user.getId()));
                    user.setRole(roleRepository.getRoleByUserId(user.getId()));
                    user.setBasket(basketRepository.getBasketByUserId(user.getId()));
                    return user;
                }
                throw new ApplicationBaseException("Invalid entity login or password: " + login + " " + password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
    }

    public boolean isLoginExists(String login) {
        return authenticateRepository.isLoginExists(login);
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
