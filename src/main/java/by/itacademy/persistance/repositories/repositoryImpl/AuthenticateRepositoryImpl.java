package by.itacademy.persistance.repositories.repositoryImpl;

import by.itacademy.exception.ApplicationBaseException;
import by.itacademy.model.users.Authenticate;
import by.itacademy.persistance.repositories.AbstractCrudRepository;
import by.itacademy.persistance.mapper.ResultSetMapper;
import by.itacademy.persistance.mapper.impl.AuthenticateResultSetMapper;
import by.itacademy.persistance.query.JdbcSqlQueryHolder;
import by.itacademy.persistance.query.impl.AuthenticateSqlQueryHolder;
import by.itacademy.persistance.statement.StatementInitializer;
import by.itacademy.persistance.statement.impl.AuthenticateStatementInitializer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthenticateRepositoryImpl extends AbstractCrudRepository<Authenticate> {

    private final JdbcSqlQueryHolder authJdbcSqlQueryHolder;
    private final StatementInitializer<Authenticate> authStatementInitializer;
    private final ResultSetMapper<Authenticate> authResultSetMapper;

    public AuthenticateRepositoryImpl() {
        authJdbcSqlQueryHolder = new AuthenticateSqlQueryHolder();
        authStatementInitializer = new AuthenticateStatementInitializer();
        authResultSetMapper = new AuthenticateResultSetMapper();
    }

    @Override
    public void create (Authenticate authenticate) {
        super.create(authenticate);
    }

    @Override
    public Authenticate getById (long id) {
        return super.getById(id);
    }

    @Override
    public List<Authenticate> getAll() {
        return super.getAll();
    }

    @Override
    public Authenticate update(Authenticate authenticate) {
        return super.update(authenticate);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    public boolean isLoginExists (String login) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select a.LOGIN " +
                     "from AUTHENTICATE a where a.login = ?")) {
            prStmt.setString(1, login);
            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    return true;
                }
                throw new ApplicationBaseException("Invalid entity login or password: " + login);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
    }

    @Override
    protected JdbcSqlQueryHolder getSqlHolder() {
        return authJdbcSqlQueryHolder;
    }

    @Override
    protected ResultSetMapper<Authenticate> getResultSetMapper() {
        return authResultSetMapper;
    }

    @Override
    protected StatementInitializer<Authenticate> getStatementInitializer() {
        return authStatementInitializer;
    }
}
