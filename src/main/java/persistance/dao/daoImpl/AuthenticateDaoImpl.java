package persistance.dao.daoImpl;

import exception.ApplicationBaseException;
import model.users.Authenticate;
import model.users.User;
import persistance.dao.AbstractCrudDao;
import persistance.mapper.ResultSetMapper;
import persistance.mapper.impl.AuthenticateResultSetMapper;
import persistance.query.JdbcSqlQueryHolder;
import persistance.query.impl.AuthenticateSqlQueryHolder;
import persistance.statement.StatementInitializer;
import persistance.statement.impl.AuthenticateStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthenticateDaoImpl extends AbstractCrudDao<Authenticate> {

    private final JdbcSqlQueryHolder authJdbcSqlQueryHolder;
    private final StatementInitializer<Authenticate> authStatementInitializer;
    private final ResultSetMapper<Authenticate> authResultSetMapper;

    public AuthenticateDaoImpl() {
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
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
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
